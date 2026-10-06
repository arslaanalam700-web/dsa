# Write your MySQL query statement below
select p.product_id , coalesce(p.new_price , 10) as price
from Products p 
where p.change_date <= '2019-08-16'
and p.change_date = (
    select max(p2.change_date)
    from Products p2
    where p2.product_id = p.product_id
    and p2.change_date <= '2019-08-16'
)
union 
select product_id, 10 as price
from Products p
where p.product_id not in(
    select product_id
    from Products 
    where change_date <= '2019-08-16'
);