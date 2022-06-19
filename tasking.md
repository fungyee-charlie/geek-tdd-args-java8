# Args tasking
## 1.first tasking

1. single option
    - boolean option
    - int option
    - String option


2. multi option
    - -l -p 8080 -d /usr/logs
    - -g this is a list -d 1 2 -3 5

3. default value
    - boolean default is false
    - int default is zero
    - string default is ""
4. unhappy path
    - extra value

## 2. second tasking
选择更小的粒度进行测试
BooleanOptionParserTest:
// sad path:
// TODO: -bool -l t / -l t f
// default:
// TODO: - bool : false

SingleValuedOptionParserTest:
// sad path:
// TODO: - int -p/ -p 8080 8081
// TODO: - string -d/ -d /usr/logs /usr/vars
// default value:
// TODO: -int :0
// TODO: - string ""


