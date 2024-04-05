package skhu.jijijig.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skhu.jijijig.service.CrawlingService;

@Slf4j
@Tag(name = "Crawling API", description = "크롤링 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CrawlingController {
    private final CrawlingService crawlingService;

    @Operation(summary = "모든 사이트 크롤링", description = "모든 사이트의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "모든 사이트 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling")
    public ResponseEntity<String> crawledWebSites() {
        try {
            crawlingService.crawlPpomppuDomestic();
            crawlingService.crawlPpomppuOverseas();
            crawlingService.crawlClien();
            crawlingService.crawlRuliweb();
            crawlingService.crawlCoolenjoy();
            crawlingService.crawlQuasarzone();
            return ResponseEntity.ok("모든 사이트의 Body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "뽐뿌(국내게시판) 크롤링", description = "뽐뿌(국내게시판)의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "뽐뿌(국내게시판) 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/ppomppu")
    public ResponseEntity<String> crawledPpomppu() {
        try {
            crawlingService.crawlPpomppuDomestic();
            return ResponseEntity.ok("뽐뿌(국내게시판)의 Body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("뽐뿌(국내게시판) 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("뽐뿌(국내게시판) 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "뽐뿌(해외게시판) 크롤링", description = "뽐뿌(해외게시판)의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "뽐뿌(해외게시판) 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/ppomppu4")
    public ResponseEntity<String> crawledPpomppu4() {
        try {
            crawlingService.crawlPpomppuOverseas();
            return ResponseEntity.ok("뿜뿌(해외게시판)의 body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("뽐뿌(해외게시판) 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("뽐뿌(해외게시판) 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "클리앙 크롤링", description = "클리앙의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "클리앙 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/clien")
    public ResponseEntity<String> crawledClien() {
        try {
            crawlingService.crawlClien();
            return ResponseEntity.ok("클리앙의 body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("클리앙 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("클리앙 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "루리웹 크롤링", description = "루리웹의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "루리웹 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/ruliweb")
    public ResponseEntity<String> crawledRuliweb() {
        try {
            crawlingService.crawlRuliweb();
            return ResponseEntity.ok("루리웹의 body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("루리웹 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("루리웹 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "쿨엔조이 크롤링", description = "쿨엔조이의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿨엔조이 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/coolenjoy")
    public ResponseEntity<String> crawledCoolenjoy() {
        try {
            crawlingService.crawlCoolenjoy();
            return ResponseEntity.ok("쿨엔조이의 body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("쿨엔조이 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("쿨엔조이 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }

    @Operation(summary = "퀘사이존 크롤링", description = "퀘사이존의 내용을 크롤링하여 결과를 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "퀘사이존 크롤링 성공"),
            @ApiResponse(responseCode = "500", description = "서버 에러")
    })
    @GetMapping("/crawling/quasarzone")
    public ResponseEntity<String> crawledQuasarzone() {
        try {
            crawlingService.crawlQuasarzone();
            return ResponseEntity.ok("퀘사이존의 body 내용이 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.error("퀘사이존 크롤링 과정에서 오류 발생", e);
            return ResponseEntity.internalServerError().body("퀘사이존 크롤링 과정에서 서버 에러가 발생했습니다.");
        }
    }
}