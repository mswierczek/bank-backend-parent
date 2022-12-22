account_service_session=$(screen -list | grep account-service-screen | awk -F" " '{print $1}')
screen -X -S $account_service_session quit
transaction_service_session=$(screen -list | grep transaction-service-screen | awk -F" " '{print $1}')
screen -X -S $transaction_service_session quit
