use quanlybanhang;
-- SELECT oid, odate, ototalprice AS oPrice
-- FROM orders;#Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order

-- select distinct c.cid,c.name,p.pname
-- from orders o
-- join customers c on o.cid = c.cid
-- join orderdetail od on o.cid = od.oid
-- join product p on od.pid = p.pid;#Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách

-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
-- SELECT c.name 
-- FROM customers c 
-- LEFT JOIN orders o ON o.cid = c.cid 
-- LEFT JOIN orderdetail od ON o.oid = od.oid 
-- WHERE od.oid IS NULL;

-- #Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn
--  (giá một hóađơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn.
--  Giá bán của từng loại được tính = odQTY*pPrice)
-- SELECT o.oid, o.odate, SUM(p.pprice * od.odqty) AS oPrice
-- FROM orders o 
-- JOIN orderdetail od ON o.oid = od.oid 
-- JOIN product p ON od.pid = p.pid 
-- GROUP BY o.oid;