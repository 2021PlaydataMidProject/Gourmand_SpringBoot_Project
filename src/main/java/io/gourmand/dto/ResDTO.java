package io.gourmand.dto;

import java.math.BigDecimal;
import java.util.List;

import io.gourmand.domain.Res;
import io.gourmand.domain.ResImg;
import io.gourmand.util.NaverGeoCoding;
import io.gourmand.util.NaverSearch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 가게 table에 주소 column 추가 고려
public class ResDTO {

	// 가게 상세페이지의 가게DTO
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class ResInfo {
		private Long resNum;
		private String resName;
		private BigDecimal xValue;
		private BigDecimal yValue;
		private String resAddress;
		private String tel;
		private BigDecimal avgStar;
		private String category;
		private List<ResImg> resImg;

		public static ResInfo of(Res res) {
			List<ResImg> rmg = null;
			if (res.getResImg().size() > 0) {
				rmg = res.getResImg();
			}
			return ResInfo.builder().resNum(res.getResNum()).resName(res.getResName()).xValue(res.getXValue())
					.yValue(res.getYValue()).resAddress(res.getResAddress()).tel(res.getTel()).avgStar(res.getAvgStar())
					.category(res.getCategory()).resImg(res.getResImg()).build();

		}
	}

	// 대표 이미지와 간단 설명
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class ResThumbnail {
		private Long resNum;
		private String resName;
		private String tel;
		private BigDecimal avgStar;
		private String category;
		private ResImg resImg;
		private String resAddress;

		public static ResThumbnail of(Res res) {
			ResImg rImg = null;
			if (res.getResImg().size() > 0) {
				rImg = res.getResImg().get(0);
			}
			return ResThumbnail.builder().resNum(res.getResNum()).resName(res.getResName()).tel(res.getTel())
					.avgStar(res.getAvgStar()).resAddress(res.getResAddress()).category(res.getCategory()).resImg(rImg)
					// 대표 이미지 선택방법 고려해야함
					.build();
		}
	}

	// 가게 등록 페이지
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	@Builder
	public static class ResRegister {
		private String resName;
		private String tel;
		private String category;

		public static Res toEntity(ResRegister res) {
			String resAddress = NaverSearch.searchPlace(res.getResName());
			BigDecimal[] axis = NaverGeoCoding.returnAxis(resAddress);
			return Res.builder().resName(res.getResName()).xValue(axis[1]).yValue(axis[0])
					.resAddress(resAddress).tel(res.getTel()).category(res.getCategory())
					.avgStar(BigDecimal.valueOf(0.00)).build();
		}
	}
}
