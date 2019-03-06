#sql("find")
  select * from deposite where c_id>? and c_id<?
#end


-- 上面代码指定了namespace为”japan”，在使用的时候，只需要在key前面添加namesapce值前缀 + 句点符 + key即可：
#namespace("japan")
  #sql("japan.find")
    select * from deposite where c_id>? and c_id<?
  #end
#end