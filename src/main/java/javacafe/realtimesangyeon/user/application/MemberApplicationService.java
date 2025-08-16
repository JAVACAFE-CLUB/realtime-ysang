package javacafe.realtimesangyeon.user.application;

import javacafe.realtimesangyeon.user.domain.Member;
import javacafe.realtimesangyeon.user.domain.MemberRepository;
import javacafe.realtimesangyeon.user.presentation.dto.MemberRequest;
import javacafe.realtimesangyeon.user.presentation.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberApplicationService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponse createMember(MemberRequest memberRequest) {
        Member user = new Member(
                memberRequest.getName(),
                memberRequest.getEmail(),
                memberRequest.getPassword()
        );
        memberRepository.save(user);
        return MemberResponse.from(user);
    }

    @Transactional(readOnly = true)
    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return MemberResponse.from(member);
    }

    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        member.updateProfile(memberRequest.getEmail(), memberRequest.getPassword());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    @Transactional
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {  // 존재 여부 확인
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
