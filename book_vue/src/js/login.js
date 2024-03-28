import {ref} from 'vue';
import axios from 'axios';
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

export default {
    setup() {
        const formData = ref({
            username: '',
            password: ''
        });

        const formRules = {
            username: [
                {required: true, message: '至少输入 1 个字符', trigger: 'blur'}
            ],
            password: [
                {required: true, min: 8, message: '至少输入 8 个字符', trigger: 'blur'}
            ]
        };

        let res = {
            code: false,
            msg: '',
            data: null
        };



        const login = (ref) => {
            ref.validate((valid) => {
                if (valid) {
                    // Submit the form or perform login action
                    // For example:
                    axios.post('http://localhost:8080/login', formData.value)
                        .then(response => {
                            res = response.data;

                            if (res.code === true) {
                                let token= res.data;
                                localStorage.setItem('token',token)
                                ElMessage.success('登入成功')
                                router.replace('/home').catch(error => {
                                    console.error(error);
                                });

                            } else {
                                ElMessage.error(res.msg);
                            }
                        })
                        .catch(error => {
                            console.error(error);
                        });
                } else {
                    return false;
                }
            });
        };

        const register = () => {
            router.replace('/register').catch(error => {
                console.error(error);
            });
        };

        return {
            formData,
            formRules,
            login,
            register
        };

    }
};