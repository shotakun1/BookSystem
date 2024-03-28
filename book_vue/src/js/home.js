import axios from 'axios'
import {onMounted, ref} from 'vue';
import HomeTable from "@/components/HomeTable.vue";
import HomeDialog from "@/components/HomeDialog.vue";
import router from "@/router/index.js";
import HomeFooter from "@/components/HomeFooter.vue";
import HomeHeader from "@/components/HomeHeader.vue";
import {ElMessage, ElMessageBox} from "element-plus";

export default {
    components: {HomeFooter, HomeTable, HomeDialog, HomeHeader},
    setup() {
        const books = ref([]); // 书籍数据
        const popup = ref(false); // 控制对话框显示状态
        const editedBook = ref({ // 正在编辑的书籍
            title: '',
            author: '',
            publisher: '',
            price: ''
        });
        const formRules = {
            title: [
                {required: true, message: 'Please enter title', trigger: 'blur'}
            ],
            author: [
                {required: true, message: 'Please enter author', trigger: 'blur'}
            ],
            publisher: [
                {required: true, message: 'Please enter publisher', trigger: 'blur'}
            ],
            price: [
                {required: true, message: 'Please enter price', trigger: 'blur'}
            ]
        };
        const addOption = ref(false);
        const updateOption = ref(false);
        const username = ref('');
        const title = ref('');
        const tableLength = ref(0)
        const currentPage = ref(1)
        const currentTablePage = ref(1)

        let res = {
            code: false,
            msg: '',
            data: null
        };

        const config = {
            headers: {
                'Authorization': `${localStorage.getItem('token')}`
            }
        }

        const url = 'http://localhost:8080/books';

        const loading = ref(false)
        const initialize = () => {
            getUser()
            getAllBooks()
        };


        const getUser = () => {
            axios.get('http://localhost:8080/user_info', config)
                .then(response => {
                    loading.value = true;

                    res = response.data;
                    username.value = res.data.username;
                    console.info(username)
                })
                .catch(error => {
                    actionWhenError('Error fetching User', error);
                })
                .finally(() => {
                    loading.value = false; // 请求完成后隐藏 loading
                })

        };

        const getAllBooks = () => {
            axios.get(url, config)
                .then(response => {
                    res = response.data;
                    books.value = res.data;
                    tableLength.value = books.value.length;
                })
                .catch(error => {
                    console.error('Error fetching books', error);
                });
        }

        const searchByOption = (searchText, selectAttr) => {
            if (searchText && selectAttr) {
                axios.get(url + '/' + searchText + '/' + selectAttr, config)
                    .then(response => {
                        res = response.data;
                        console.info(res)
                        books.value = res.data;
                        tableLength.value = books.value.length;
                    })
                    .catch(error => {
                        actionWhenError('Error fetching books', error);
                    });
            } else {
                ElMessage.warning('检测到下拉框或输入框为空，自动查询全部书籍')
                getAllBooks()
            }
        };

        const addBook = (ref) => {
            ref.validate(valid => {
                if (valid) {
                    axios.post(url, editedBook.value, config)
                        .then(() => {
                            actionWhenSuccess("图书已添加");
                            popup.value = false;
                        })
                        .catch(error => {
                            actionWhenError('Error adding book', error);
                        });
                } else {
                    return false;
                }
            });
        };
        const deleteBook = (book) => {

            axios.delete(url + '/' + book.bookId, config)
                .then(() => {
                    actionWhenSuccess("图书已删除");
                })
                .catch(error => {
                    actionWhenError('Error deleting book', error);
                });

        };
        const updateBook = (ref) => {
            ref.validate(valid => {
                if (valid) {
                    axios.put(url, editedBook.value, config)
                        .then(() => {
                            actionWhenSuccess("图书已更新");
                            popup.value = false;
                        })
                        .catch(error => {
                            actionWhenError('Error updating book', error);
                        });
                } else {
                    return false;
                }
            });
        };

        const actionWhenSuccess = (msg) => {
            console.log(msg);
            ElMessage.success(msg);
            // window.location.reload();
            getAllBooks()
        }

        const actionWhenError = (msg, error) => {
            console.error(msg, error);
            ElMessage.error('令牌不存在或无效')
            router.replace('/login').catch(error => {
                console.error(error);
            });
        }
        const openAddPopup = () => {
            editedBook.value = {
                title: '',
                author: '',
                publisher: '',
                price: ''
            };
            addOption.value = true;
            popup.value = true;
            title.value = '新增图书信息'
        };
        const openUpdatePopup = (book) => {
            editedBook.value = {...book};
            updateOption.value = true;
            popup.value = true;
            title.value = '更改图书信息'
        };
        const closePopup = (ref) => {
            addOption.value = false; // Close the popup window
            updateOption.value = false;
            popup.value = false;
            ref.resetFields();
        };

        const logout = () => {
            ElMessageBox.confirm('确定退出登录？', 'warning')
                .then(() => {
                    ElMessage({
                        type: 'success',
                        message: '已退出登录',
                    })
                    localStorage.removeItem('token');
                    router.replace('/login').catch(error => {
                        console.error(error);
                    });
                })
        }

        const handleCurrentChange = (val) => {
            currentPage.value = val
            currentTablePage.value = currentPage.value
            // books.value = books.value.slice()
            // console.info(val)
        }

        onMounted(initialize);

        return {
            loading,
            title,
            username,
            books,
            popup,
            editedBook,
            formRules,
            addOption,
            updateOption,
            tableLength,
            currentPage,
            currentTablePage,
            getUser,
            getAllBooks,
            openAddPopup,
            deleteBook,
            openUpdatePopup,
            updateBook,
            addBook,
            closePopup,
            searchByOption,
            logout,
            handleCurrentChange
        };
    }

};
