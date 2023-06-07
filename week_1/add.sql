-- Hiển thị danh sách tất cả học viên
use quanlysinhvien;
-- SELECT *
-- FROM Student;

-- #Hiển thị danh sách học viên đang theo học
-- SELECT *
-- FROM Student
-- WHERE Status = true;

-- #Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ.
-- SELECT *
-- FROM Subject
-- WHERE Credit < 10;

-- #Hiển thị danh sách học viên lớp A1
-- SELECT S.StudentId, S.StudentName, C.ClassName
-- FROM Student S join Class C on S.ClassId = C.ClassID
-- WHERE C.ClassName = 'A1';

-- #Hiển thị điểm môn CF của các học viên.
-- SELECT S.StudentId, S.StudentName, Sub.SubName, M.Mark
-- FROM Student S join Mark M on S.StudentId = M.StudentId join Subject Sub on M.SubId = Sub.SubId
-- WHERE Sub.SubName = 'CF';
-- select * from student where studentname like 'h%';# hiển thị thông tin học sinh có bắt đầu bằng chữ H
-- select * from class where month(startdate) = 12;# hiện thị các lớp 	học có thời gian bắt đầu ở tháng 12
-- select * from subject where credit between 3 and 5;# hiển thị tất cả các môn học có credit từ 3 đến 5
-- update student set classid = 2 where studentname = 'hung';#thay đổi classid của học sinh có tên là hùng
-- SELECT Student.StudentName, Subject.SubName, Mark.Mark
-- FROM Student 
-- JOIN Mark ON Student.StudentID = Mark.StudentID 
-- JOIN Subject ON Mark.SubID = Subject.SubID 
-- ORDER BY Mark.Mark DESC, Student.StudentName ASC#Hiển thị các thông tin: StudentName, SubName, Mark. Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
