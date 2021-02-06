package io.gourmand.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "THREAD")
public class Thread {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "THREAD_NUM")
   private Long threadNum;
   
   @ManyToOne
   @JoinColumn(name= "REVIEW_NUM")
   private Review reviewNum;
   
   @Column(name="USER_ID")
   private String userId;
   
   @Column(name="REVIEW_THREAD")
   private String reviewThread;
   
   @Column(name="REVIEW_THREAD_DATE")
   private LocalDate reviewThreadDate;
}