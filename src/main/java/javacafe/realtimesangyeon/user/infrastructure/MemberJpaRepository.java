package javacafe.realtimesangyeon.user.infrastructure;

import javacafe.realtimesangyeon.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
