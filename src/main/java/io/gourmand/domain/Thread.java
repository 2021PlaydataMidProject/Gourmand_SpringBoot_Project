package io.gourmand.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity(name = "THREAD")
public class Thread {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "THREAD_NUM")
   private Long threadNum;
   @OneToMany(mappedBy="REVIEW_NUM")
   private List<Review> reviewNum;
   @Column(name="USER_ID")
   private String userId;
   @Column(name="REVIEW_THREAD")
   private String reviewThread;
   @Column(name="REVIEW_THREAD_DATE")
   private LocalDate reviewThreadDate;
}