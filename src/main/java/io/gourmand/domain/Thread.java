package io.gourmand.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
   private Long threadnum;
   
   @Column(name="REVIEW_NUM")
   private Long review_num;
   
   @Column(name="USER_ID")
   private String userid;
   
   @Column(name="REVIEW_THREAD")
   private String reviewthread;
   
   @Column(name="REVIEW_THREAD_DATE")
   private LocalDate reviewthreaddate;
   
}