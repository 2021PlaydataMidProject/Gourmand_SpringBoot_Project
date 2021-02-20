//package io.gourmand;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import io.gourmand.service.RevService;
//import io.gourmand.dao.ReviewRepository;
//
//public class ReviewServiceTests {
//
//    @InjectMocks
//    private RevService reviewService;
//
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void addReview() {
//    	reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");
//
//        verify(reviewRepository).save(any());
//    }
//
//}