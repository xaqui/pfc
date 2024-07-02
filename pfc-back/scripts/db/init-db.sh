echo "########### Running SQL script against DB ###########"
mysql --user="root" --password="root" --database="excursiondb" < /tmp/sql/createtables.sql || echo
echo "########### Script execution finished! ###########"