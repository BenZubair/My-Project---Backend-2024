package swahili.cafe.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swahili.cafe.application.model.AcademicProfile;
import swahili.cafe.application.model.Expert;
import swahili.cafe.application.repository.AcademicProfileRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicProfileService {
    private final AcademicProfileRepository profileRepository;
    private final UserService userService;

    public AcademicProfile createAcademicProfile(AcademicProfile academicProfile, long expertId)  {
        Expert expert = (Expert) userService.findUserByUserId(expertId);
        academicProfile.setExpert(expert);
        return profileRepository.save(academicProfile);
    }

    public List<AcademicProfile> getAllAcademicProfilesByExpertUsername(String username) {
        return profileRepository.getAllAcademicProfilesByExpertUsername(username);
    }


    public List<AcademicProfile> getAllAcademicProfilesByExpertUserId(long userId) {
        return profileRepository.getAllAcademicProfilesByExpertUserId(userId);
    }
}
