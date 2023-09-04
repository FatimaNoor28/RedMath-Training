<template>
    <div class="container">
        <h1 class="text-center">Add Balance</h1>
        <form @submit.prevent="makeTransaction">
            <!-- Form fields here -->
            <div class="form-group">
                <label for="balance_id">Balance ID</label>
                <input type="text" id="balance_id" v-model="balance.balance_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="account_id">Account ID</label>
                <input type="text" id="account_id" v-model="balance.account_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="amount">Amount</label>
                <input type="text" id="amount" v-model="balance.amount" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="db_cr">DB/CR</label>
                <input type="text" id="db_cr" v-model="balance.db_cr" class="form-control" required>
            </div>
            <!-- Other form fields -->
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>

<script>
import BalanceService from '@/services/BalanceService';

export default {
    name: 'AddBalance',
    data() {
        return {
            balance: {
                account_id: '',
                description: '',
                amount: '',
                db_cr: '',
                date: ''
            }
        };
    },
    created() {
        this.addBalance();
        // this.editAccounts();
    },
    methods: {
        addBalance() {
            // Call your backend service to make the transaction
            BalanceService.addBalance(this.balance)
                .then((response) => {
                    // Handle the response, e.g., show a success message
                    console.log('Balance Added:', response.data);
                    // Clear the form
                    this.balance = {
                        account_id: '',
                        description: '',
                        amount: '',
                        db_cr: '',
                        date: ''
                    };
                })
                .catch((error) => {
                    // Handle errors, e.g., show an error message
                    console.error('Balance failed:', error);
                });
        }
    }
};
</script>
