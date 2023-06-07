use quanlysinhvien;
# hiện thị tất cả thông tin môn học có credit lớn nhất
-- select *
-- from subject 
-- where Credit =(select max(credit) from subject)
-- ;
#hiện thị các thông tin môn học có điểm thi lớn nhất
-- select * 
-- from mark
-- inner join subject on mark.subid = subject.subid
-- where mark = (select max(mark) from mark );
#hiện thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên theo thứ tự giảm dần
select s.studentid,s.studentname,avg(m.mark) as avgmark
from mark m 
join student s  on m.studentid = s.StudentId
group by s.StudentId,s.StudentName
order by avgmark desc;