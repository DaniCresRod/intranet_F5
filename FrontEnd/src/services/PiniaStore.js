import {defineStore} from 'pinia'

export const myUserStore=defineStore('userData', {
    state:()=>({
        uEmail :'',
        uName:'',
        uPass:'',
    }),
});