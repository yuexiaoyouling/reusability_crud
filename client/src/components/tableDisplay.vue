<template>

    <div class="center-container">

        <el-form :inline="true" :model="msg" class="demo-form-inline">
            <el-form-item label="学生ID">
                <el-input v-model="msg.sId" placeholder="学生ID"></el-input>
            </el-form-item>
            <el-form-item label="学生名称">
                <el-input v-model="msg.sName" placeholder="学生名称"></el-input>
            </el-form-item>
            <el-form-item label="年级">
                <el-select v-model="msg.grade.cName">
                    <el-option v-for="grade in grades" :key="grade.cId" :value="grade.cName"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
            <br />
            <el-form-item label="java成绩" v-show="status">
                <el-input v-model="msg.Acj" placeholder="java成绩"></el-input>
            </el-form-item>
            <el-form-item label="Android成绩" v-show="status">
                <el-input v-model="msg.Bcj" placeholder="Android成绩"></el-input>
            </el-form-item>
            <el-form-item label="javaEE成绩" v-show="status">
                <el-input v-model="msg.Ccj" placeholder="javaEE成绩"></el-input>
            </el-form-item>

        </el-form>
        <el-table :data="tableData" border cell-style="text-align:center">
            <el-table-column label="Image" min-width="100" prop="logo" header-align="center">
                <template slot-scope="scope">
                    <img :src="scope.row.logo" alt="" style="width: 36px;height: 36px" />
                </template>
            </el-table-column>
            <el-table-column label="学生ID" min-width="50" prop="sId" header-align="center"></el-table-column>
            <el-table-column label="学生姓名" min-width="100" prop="sName" header-align="center"></el-table-column>
            <el-table-column label="Java成绩" min-width="50" prop="Acj" header-align="center"></el-table-column>
            <el-table-column label="Android成绩" min-width="50" prop="Bcj" header-align="center"></el-table-column>
            <el-table-column label="JavaEE成绩" min-width="50" prop="Ccj" header-align="center"></el-table-column>
            <el-table-column label="总成绩" min-width="50" prop="Zcj" header-align="center"></el-table-column>
            <el-table-column label="操作" header-align="center">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" plain @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button type="danger" icon="el-icon-delete" plain @click="handleDelete(scope.$index, scope.row)">
                        删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="page.currentPage" :page-sizes="[5, 10, 15, 20]" :page-size="page.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="page.total">
            </el-pagination>
        </div>
        <el-dialog title="修改学生信息" :visible.sync="dialogVisible" width="50%" :before-close="handleClose">
            <el-form ref="form" :model="form" :rules="rules">

                <el-form-item label="姓名" prop="sName">
                    <el-input v-model="form.sName"></el-input>
                </el-form-item>
                <el-form-item label="JAVA成绩" prop="Acj">
                    <el-input v-model="form.Acj"></el-input>
                </el-form-item>
                <el-form-item label="Android成绩" prop="Bcj">
                    <el-input v-model="form.Bcj"></el-input>
                </el-form-item>
                <el-form-item label="JavaEE成绩" prop="Ccj">
                    <el-input v-model="form.Ccj"></el-input>
                </el-form-item>
                <el-form-item label="更新头像" prop="logo">
                    <el-image alt="" :src="form.logo" style="width: 100px; height: 100px" :fit="fit" />
                </el-form-item>
                <el-upload class="upload-demo" action="https://jsonplaceholder.typicode.com/posts/"
                    :on-progress="handlePictureCardPreview" list-type="picture" :auto-upload="true">
                    <el-button size="small" style="position: absolute;left:10px; bottom:70px">点击上传</el-button>
                </el-upload>
                <el-button type="danger" @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateStudent">确 定</el-button>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import { searchAllClass } from "../api"
import { searchStudentByAny } from "../api"
import { searchAllStudent } from "../api"
import { removeStudent } from "../api"
import { updateStudent } from "../api"
export default {
    name: "tableDisplay",
    data() {
        //校验邮箱的规则
        const checkScore = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('输入不可以为空'));
            }
            setTimeout(() => {
                if (!Number(value)) {
                    callback(new Error('请输入整数，值为[0,150]'));
                } else {
                    if (value < 0 || value > 150) {
                        callback(new Error('请输入整数，值为[0,150]'));
                    } else {
                        callback();
                    }
                }
            }, 100);
        }
        const checkName = (rule, value, callback) => {
            const reg = /^[\u4E00-\u9FFF\u3400-\u4DBF\uF900-\uFAFF·]{2,20}$/;
            if (!reg.test(value)) {
                callback(new Error('请输入有效的名字'));
            }
            callback();
        }
        return {
            fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
            imgStr: require('../assets/upload.png'),
            tableData: [],
            page: {
                currentPage: 1,
                pageSize: 10,
                total: ""
            },
            grades: [],
            msg: {
                sId: "",
                sName: "",
                Acj: "",
                Bcj: "",
                Ccj: "",
                grade: {
                    cId: "",
                    cName: ""
                }
            },
            status: false,
            dialogVisible: false,
            form: {},
            // 修改用户表单的验证规则对象
            rules: {
                sName: [
                    {
                        required: true,
                        message: '请输入姓名',
                        trigger: 'blur'
                    },
                    {
                        // eslint-disable-next-line no-undef
                        validator: checkName,
                        trigger: 'blur'
                    }
                ],
                Acj: [
                    {
                        required: true,
                        message: '请输入JAVA成绩',
                        trigger: 'blur'
                    },
                    {
                        // eslint-disable-next-line no-undef
                        validator: checkScore,
                        trigger: 'blur'
                    }
                ],
                Bcj: [
                    {
                        required: true,
                        message: '请输入Android成绩',
                        trigger: 'blur'
                    },
                    {
                        // eslint-disable-next-line no-undef
                        validator: checkScore,
                        trigger: 'blur'
                    }
                ],
                Ccj: [
                    {
                        required: true,
                        message: '请输入javaEE成绩',
                        trigger: 'blur'
                    },
                    {
                        // eslint-disable-next-line no-undef
                        validator: checkScore,
                        trigger: 'blur'
                    }
                ]

            }
        }
    },
    methods: {

        handlePictureCardPreview(event, file) {
            this.form.logo = file.url;
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                // eslint-disable-next-line no-unused-vars
                .then(_ => {
                    done();
                })
                // eslint-disable-next-line no-unused-vars
                .catch(_ => { });
        },
        // 初始页currentPage、初始每页数据数pagesize和数据data
        handleSizeChange(size) {
            if (this.page.currentPage == undefined) {
                this.page.currentPage = 1
            }
            this.page.pageSize = size;
            this.display();
        },
        handleCurrentChange(currentPage) {
            this.page.currentPage = currentPage;
            this.display();
        },
        async handleDelete(index, row) {
            const confirmR = await this.$confirm(
                "此操作将删除该数据, 是否继续?",
                "提示",
                {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning",
                    center: true,
                }
            ).catch((err) => err);
            if (confirmR == "confirm") {
                try {
                    removeStudent(row.sId)
                    this.tableData.splice(index, 1)
                } catch (error) {
                    // 请求失败的回调
                    console.log(index + 1 + "is error");
                }
            }
        },
        handleEdit(index, row) {

            this.form = JSON.parse(JSON.stringify(row))
            localStorage.setItem('index', index)
            this.dialogVisible = true;
        },

        updateStudent() {
            try {
                this.form.Zcj = Number(this.form.Acj) + Number(this.form.Bcj) + Number(this.form.Ccj);
                updateStudent(this.form.sId, this.form.sName, this.form.Acj, this.form.Bcj, this.form.Ccj, this.form.logo);
                let index = localStorage.getItem('index')
                let onceTabel = this.tableData
                onceTabel[index] = this.form
                this.tableData = this.tableData.filter(item => item);

            } catch (error) {
                // 请求失败的回调
                console.log(error);
            }
            this.dialogVisible = false
        },
        async display() {
            //当前页
            var current = this.page.currentPage//请求所需的参数 根据后端接口要求来
            //每页显示条数
            var pageSize = this.page.pageSize//请求所需的参数 根据后端接口要求来
            // 接口
            try {
                // 调用接口
                let result = await searchAllStudent(current, pageSize)
                let pageInfo = result.data.dataMap.pageInfo
                // 输出请求后的返回值
                this.tableData = pageInfo.list;
            } catch (error) {
                // 请求失败的回调
                console.log(error)
            }
        },
        async onSubmit() {
            //当前页
            var current = this.page.currentPage;//请求所需的参数 根据后端接口要求来
            //每页显示条数
            if (current == undefined) {
                current = 1;
            }
            var pageSize = this.page.pageSize;//请求所需的参数 根据后端接口要求来
            var sId = this.msg.sId;
            var sName = this.msg.sName;
            var grade = this.msg.grade;
            try {
                // 调用接口
                let result = await searchStudentByAny(current, pageSize, sId, sName, grade.cName);
                let pageInfo = result.data.dataMap.pageInfo;
                this.tableData = pageInfo.list;
                this.page = pageInfo;
                if (pageInfo.list.length == 1) {
                    let st = pageInfo.list[0]
                    this.msg.Acj = st.Acj;
                    this.msg.Bcj = st.Bcj;
                    this.msg.Ccj = st.Ccj;
                    this.status = true;
                } else if (pageInfo.list.length == 0 || pageInfo.list.length > 1) {
                    this.status = false;
                }
            } catch (error) {
                // 请求失败的回调
                console.log(error)
            }
            if (this.msg.sId == "" || this.msg.sName == "" || this.msg.grade.cName == "") {
                let msg = await searchAllStudent(current, pageSize);
                let pageInfo = msg.data.dataMap.pageInfo;
                // 输出请求后的返回值
                this.tableData = pageInfo.list;
                this.page = pageInfo;
                this.status = false;
            }
        }

    },
    mounted: function () {
        this.$nextTick(async function () {
            try {
                // 调用接口
                let msg = await searchAllStudent(1, 10)
                let pageInfo = msg.data.dataMap.pageInfo
                // 输出请求后的返回值
                this.tableData = pageInfo.list;
                this.page = pageInfo
                // 调用接口
                let result = await searchAllClass();
                let data = result.data.dataMap.grades
                // 输出请求后的返回值
                this.grades = data
            } catch (error) {
                // 请求失败的回调
                console.log(error)
            }


        })
    }

}
</script>

<style  scoped>

</style>