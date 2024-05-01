
document.getElementById('addTaskButton').addEventListener('click', function() {
    var weekTasks = document.querySelector('.week-tasks');
    var newTask = document.createElement('div');
    newTask.className = 'task';
    newTask.style.backgroundColor = "antiquewhite"; // 배경색 설정
    newTask.style.display = "flex";
    newTask.style.alignItems = "center";
    newTask.style.justifyContent = "space-between";

    var taskText = document.createElement('span');
    taskText.textContent = '새 주차 과제 제출'; // 새 주차 과제 텍스트
    newTask.appendChild(taskText);

    var taskCheckbox = document.createElement('input');
    taskCheckbox.type = 'checkbox';
    taskCheckbox.className = 'task-checkbox';
    newTask.appendChild(taskCheckbox);

    // 버튼 바로 전에 새 태스크를 삽입
    var addTaskButton = document.getElementById('addTaskButton');
    weekTasks.insertBefore(newTask, addTaskButton);
});

document.querySelector('.week-tasks').addEventListener('change', function(e) {
    if (e.target.classList.contains('task-checkbox')) {
        // 체크박스 클릭 이벤트 핸들러 추가
    }
});