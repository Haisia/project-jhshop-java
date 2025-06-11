package com.haisia.shop.user.service.domain.valueobject;

public enum LedgerReason {
  LOGGED_IN_FIRST_TODAY("매일 첫 로그인"),
  SIGNUP_REWARD("회원가입 포인트 지급"),
  ADMIN_ADJUST("관리자 수동 조정"),
  PURCHASE_DEDUCTION("구매 차감"),
  REFUND("환불"),
  ;

  private final String description;

  LedgerReason(String description) {
    this.description = description;
  }

  public String description() {
    return description;
  }
}
