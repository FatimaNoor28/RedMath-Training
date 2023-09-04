import axios from 'axios';

const TRANSACTION_API_BASE_URL = 'http://localhost:8080/api/v1/transaction';

class TransactionService{

    getTransactions(){
        return axios.get(TRANSACTION_API_BASE_URL);
    }
    makeTransaction(transactionData){
        return axios.post(TRANSACTION_API_BASE_URL, transactionData);
    }
}

export default new TransactionService();