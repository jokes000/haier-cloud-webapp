#!/usr/bin/expect

#pushToTomcat.sh username ip password path warfile

set username [lindex $argv 0]
set ip [lindex $argv 1]
set password [lindex $argv 2]
set path [lindex $argv 3]
set warfile [lindex $argv 4]
set jarfile [lindex $argv 5]

spawn scp $warfile $username@$ip:$path/webapps/
expect {
	"(yes/no)?"	{
		send "yes\n"
		exp_continue
	}
	"password:" {
		send "$password\n"
		expect "100%"
		exp_continue
	}
	eof
}
spawn scp $jarfile $username@$ip:$path/webapps/ROOT/
expect {
    "(yes/no)?" {
        send "yes\n"
        exp_continue
    }
    "password:" {
        send "$password\n"
        expect "100%"
        exp_continue
    }
    eof
}
spawn ssh $username@$ip
expect {
	"(yes/no)?"	{
		send "yes\n"
		exp_continue
	}
	"password:" {
		send "$password\n"
		expect "\r\n"
		send "$path/bin/shutdown.sh\n"
		expect "tomcat-juli.jar"
		send "$path/bin/startup.sh\n"
		expect "tomcat-juli.jar"
		send "exit\n"
		expect eof 
		exit
	}
	"\r\n" {
		send "$path/bin/shutdown.sh\n"
		expect "tomcat-juli.jar"
		send "$path/bin/startup.sh\n"
		expect "tomcat-juli.jar"
		send "exit\n"
		expect eof 
		exit	
	}
	eof
}
exit
