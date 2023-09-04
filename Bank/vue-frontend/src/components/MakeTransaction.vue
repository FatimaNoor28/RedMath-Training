<template>
    <div class="container">
        <h1 class="text-center">Make Transaction</h1>
        <form @submit.prevent="makeTransaction">
            <!-- Form fields here -->
            <div class="form-group">
                <label for="account_id">Transaction ID</label>
                <input type="text" id="transaction_id" v-model="transaction.transaction_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="account_id">Account ID</label>
                <input type="text" id="account_id" v-model="transaction.account_id" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="account_id">Description</label>
                <input type="text" id="description" v-model="transaction.description" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="account_id">Amount</label>
                <input type="text" id="amount" v-model="transaction.amount" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="account_id">DB/CR</label>
                <input type="text" id="db_cr" v-model="transaction.db_cr" class="form-control" required>
            </div>
            <!-- Other form fields -->
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</template>

<script>
import TransactionService from '../services/TransactionService';

export default {
    name: 'MakeTransaction',
    data() {
        return {
            transaction: {
                account_id: '',
                description: '',
                amount: '',
                db_cr: '',
                date: ''
            }
        };
    },
    created() {
        this.makeTransaction();
        // this.editAccounts();
    },
    methods: {
        makeTransaction() {
            // Call your backend service to make the transaction
            TransactionService.makeTransaction(this.transaction)
                .then((response) => {
                    // Handle the response, e.g., show a success message
                    console.log('Transaction successful:', response.data);
                    // Clear the form
                    this.transaction = {
                        account_id: '',
                        description: '',
                        amount: '',
                        db_cr: '',
                        date: ''
                    };
                })
                .catch((error) => {
                    // Handle errors, e.g., show an error message
                    console.error('Transaction failed:', error);
                });
        }
    }
};
</script>
