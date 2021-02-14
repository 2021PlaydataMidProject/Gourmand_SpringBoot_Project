package io.gourmand.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

@Entity(name = "REPLY")
public class Reply {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "REPLY_NUM")
   private Long replyNum;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name= "REVIEW_NUM")
   private Review reviewNum;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name= "USER_NUM")
   private User user; // 한명 user - 여러개 댓글  1:N
   
   @Column(name="REVIEW_REPLY")
   private String reviewReply;
   
   @Column(name="REVIEW_REPLY_DATE")
   private LocalDate reviewReplyDate;
}