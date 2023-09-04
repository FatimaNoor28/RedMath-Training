import axios from 'axios';
// import Vue from 'vue';

// Vue.use(axios)

const ACCOUNT_API_BASE_URL = 'http://localhost:8080/api/v1/account';

class AccountService{

    getAccounts(){
        return axios.get(ACCOUNT_API_BASE_URL);
    }
    addAccount(account){
        return axios.post(ACCOUNT_API_BASE_URL, account);
    }
}

export default new AccountService();