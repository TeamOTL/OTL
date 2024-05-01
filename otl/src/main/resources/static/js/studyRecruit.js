document.querySelectorAll('.approve-btn').forEach(button => {
    button.addEventListener('click', function() {
        console.log(this.parentElement.querySelector('.applicant-name').textContent + ' 승인');
        // 서버로 승인 요청 보내기
    });
});

document.querySelectorAll('.reject-btn').forEach(button => {
    button.addEventListener('click', function() {
        console.log(this.parentElement.querySelector('.applicant-name').textContent + ' 거절');
        // 서버로 거절 요청 보내기
    });
});

document.addEventListener('DOMContentLoaded', function() {
    // 페이지가 로드될 때 이벤트 리스너를 모든 거절 버튼에 추가
    const rejectButtons = document.querySelectorAll('.reject-btn');

    rejectButtons.forEach(button => {
        button.addEventListener('click', function() {
            const applicant = this.closest('.applicant'); // 'applicant' 클래스를 가진 가장 가까운 조상 요소를 찾음
            if (applicant) {
                applicant.remove(); // 해당 신청자 요소를 DOM에서 제거
            }
        });
    });
});