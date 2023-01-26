package com.example.taas.security;

import com.example.taas.beans.ClientType;
import com.example.taas.beans.User;
import com.example.taas.exceptions.SecurityMsg;
import com.example.taas.exceptions.TaskSecurityException;
import com.example.taas.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service // (singletons) all of them managed on RAM memory
@RequiredArgsConstructor // אלעד של מחר - collection injection (config.MapConfig -> initialization bean)
public class TokenManager {

    //    @Autowired אלעד של אתמול
    //      אלעד של היום יעשה עם קולקש'ן אינג'קשן
    private final Map<UUID, Information> map;


    private final UserRepository userRepository;

    public UUID add(String email) {
        User fromDb = userRepository.findTop1ByEmail(email);
        removePreviousInstances(fromDb.getId());
        Information information = Information.builder()
                .id(fromDb.getId())
                .email(email)
                .time(LocalDateTime.now())
                .build();
        UUID token = UUID.randomUUID();
        map.put(token, information);
        return token;
    }

    public int getUserId(UUID token) throws TaskSecurityException {
        Information information = map.get(token);
        if (information == null)
            throw new TaskSecurityException(SecurityMsg.INVALID_TOKEN);
        return information.getId();
    }

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void deleteExpiredTokensOver30Minutes() {
        map.entrySet().removeIf(ins -> ins.getValue().getTime().isAfter(LocalDateTime.now().minusMinutes(30)));
    }

    public void removePreviousInstances(int userId) {
        map.entrySet().removeIf(inst -> inst.getValue().getId() == userId);
    }


}
