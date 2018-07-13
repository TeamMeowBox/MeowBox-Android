<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pay document</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
    <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script type="text/javascript">
var IMP = window.IMP; // 생략가능
IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

function myset(res){
var hey = res;

//onclick, onload 등 원하는 이벤트에 호출합니다
IMP.request_pay(
{


    pg : 'inicis', // version 1.1.0부터 지원.
    pay_method : 'card',
    merchant_uid : '402',
    name : hey,
    amount : 200,
    buyer_email : 'iamport@siot.do',
    buyer_name : '구매자이름',
    buyer_tel : '010-7473-8455',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456',
    m_redirect_url : 'http://13.124.92.40:3000/order/order_result',
    app_scheme : 'iamportapp'
}
, function(rsp) {
    if ( rsp.success ) {
        var msg = '결제가 완료되었습니다.';
        msg += '고유ID : ' + rsp.imp_uid;
        msg += '상점 거래ID : ' + rsp.merchant_uid;
        msg += '결제 금액 : ' + rsp.paid_amount;
        msg += '카드 승인번호 : ' + rsp.apply_num;
    } else {
        var msg = '결제에 실패하였습니다.';
        msg += '에러내용 : ' + rsp.error_msg;
    }

    alert(msg);
});
}


</script>

</body>
</html>