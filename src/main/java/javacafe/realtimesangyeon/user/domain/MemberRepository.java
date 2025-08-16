package javacafe.realtimesangyeon.user.domain;

import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
}
