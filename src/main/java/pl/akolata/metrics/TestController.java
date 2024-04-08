package pl.akolata.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A test controller created to present a chart showing HTTP traffic by an endpoint.
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/test")
class TestController {

    private final MeterRegistry meterRegistry;

    @GetMapping
    public ResponseEntity<String> test() {
        log.info("Received request: GET /api/test");
        Counter counter = Counter.builder(MetricUtil.API_BOOKS+"_api_test")
                .description("a number of test requests to GET /api/test endpoint")
                .register(meterRegistry);
        counter.increment();

        return ResponseEntity.ok("Test");
    }
}
