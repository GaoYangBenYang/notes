    模糊查询like语句该怎么写?

           select * from accountbook where name like  concat('%',#{key},'%')

           1.表达式: name like"%"#{key}"%" #起到占位符的作用

           2.表达式: name like '%${key}%' $进行字符串的拼接,直接把传入的值,拼接上去了,没有任何问题

           表达式: name like concat('%',#{key},'%') 这是使用了cancat进行字符串的连接,同时使用了#进行占位

           表达式:name like CONCAT('%','${key}','%') 对上面的表达式进行了简化,更方便了