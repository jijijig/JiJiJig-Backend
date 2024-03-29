package skhu.jijijig.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skhu.jijijig.domain.dto.MemberDTO;
import skhu.jijijig.domain.dto.TokenDTO;
import skhu.jijijig.domain.model.Member;
import skhu.jijijig.domain.repository.MemberRepository;
import skhu.jijijig.token.TokenProvider;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenBlackListService tokenBlackListService;
    private final TokenProvider tokenProvider;
    private final FirebaseAuth firebaseAuth;

    public MemberDTO join(MemberDTO memberDTO) {
        if (memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        Member member = Member.fromDTO(memberDTO);
        memberRepository.save(member);
        return new MemberDTO(member.getName(), member.getEmail(), member.getPicture());
    }

    public TokenDTO login(String firebaseToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(firebaseToken);
            String email = decodedToken.getEmail();
            Member member = memberRepository.findByEmail(email)
                    .orElseGet(() -> Member.fromToken(decodedToken));
            return tokenProvider.createTokens(member);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Firebase 인증 실패: " + e.getMessage());
        }
    }

    public TokenDTO refresh(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("리프레시 토큰이 유효하지 않습니다.");
        }
        return tokenProvider.renewToken(refreshToken);
    }

    public void logout(String accessToken, String refreshToken) {
        tokenBlackListService.addToBlackList(accessToken);
        tokenBlackListService.addToBlackList(refreshToken);
    }
}