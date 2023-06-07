use quanlysinhvien;
# sử dụng hàm count để hiện thị số lượng sinh viên ở từng nơi
-- select address,count(studentid) as 'số lượng học viên'
-- from student 
-- group by address
# tính điểm trung bình của học sinh
-- select s.studentid,s.studentname,avg(mark)
-- from student s join mark m on s.studentid = m.studentid
-- group by s.studentid ,s.studentname;
# hiện thị những bản học viên có điểm trung bình các môn học lớn hơn 15
-- select s.studentid,s.studentname, avg(mark)
-- from student s 
-- join mark m on s.studentid = m.studentid
-- group by s.studentid , s.studentname
-- having avg(mark ) >15;
# hiện thị thông tin các học viên có điểm trung bình lớn nhất
-- select s.studentid,s.studentname,avg(mark)
-- from student s 
-- join mark m on s.studentid = m.studentid
-- group by s.studentid, s.studentname
-- having avg(mark) >= all (select avg(mark) from mark group by mark.studentid);

