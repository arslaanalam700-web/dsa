delete from Person
where id not in(
    select *
    from (
        select min(id)
        from Person
        group by email
    )as temp
);