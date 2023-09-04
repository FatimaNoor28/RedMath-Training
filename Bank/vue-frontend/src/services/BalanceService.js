import axios from 'axios';

const BALANCE_API_BASE_URL = 'http://localhost:8080/api/v1/balance';

class BalanceService{

    getBalance(){
        return axios.get(BALANCE_API_BASE_URL);
    }
    addBalance(){
        return axios.post(BALANCE_API_BASE_URL);
    }

}

export default new BalanceService();