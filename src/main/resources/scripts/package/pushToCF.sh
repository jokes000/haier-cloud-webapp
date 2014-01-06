#!/bin/bash

set path [lindex $argv 0]

spawn cf login
expect "email"
send "351704806@qq.com"
expect "password"
send "329611907ke"
spawn pushd $path
expect "/r/n"
send "cf push"