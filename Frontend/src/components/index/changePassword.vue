<template>
    <el-dialog v-model="props.switch" title="修改密码" align-center @close="TurnInversion" destroy-on-close>
        <el-form ref="ruleFormRef" style="max-width: 600px" :model="passwordForm" :rules="passwdrules"
            label-width="auto" class="demo-ruleForm" status-icon>
            <el-form-item label="旧密码" prop="oldpassword">
                <el-input type="password" show-password v-model.trim="passwordForm.oldpassword" />
            </el-form-item>
            <el-form-item label="新密码" prop="newpassword">
                <el-input type="password" show-password v-model.trim="passwordForm.newpassword" />
            </el-form-item>
            <el-form-item label="重新确认新密码" prop="newpassword2">
                <el-input type="password" show-password v-model.trim="passwordForm.newpassword2" />
            </el-form-item>
        </el-form>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="toggleSwitch">取消</el-button>
                <el-button type="primary" @click="changePassword">
                    确定
                </el-button>
            </div>
        </template>
    </el-dialog><!-- 修改密码窗口 -->
</template>
<script setup>
import { ref, reactive, defineEmits } from 'vue';
import { apiChangepassword } from '@/api/user';

// 定义props
const props = defineProps({
    switch: Boolean,
    code: String,
});


// 定义可以触发的事件
const emits = defineEmits([
    'update:switch',
    'confirmGetData'
]);

// 定义一个方法来触发更新switch的事件
const toggleSwitch = () => {
    emits('update:switch', props.switch = false);
}

// 修改密码
const validateConfirmPassword = (rule, value, callback) => { // 校验两个密码是否一样呀
    if (value !== passwordForm.value.newpassword) {
        callback(new Error('两次输入的密码不一致'));
    } else {
        callback();
    }
}

const passwordForm = ref(
    {
        oldpassword: '',
        newpassword: '',
        newpassword2: '',
        code: '',
    }
)

// 清空表单
const initchangePassword = (code) => {
    passwordForm.value.oldpassword = ''
    passwordForm.value.newpassword = ''
    passwordForm.value.newpassword2 = ''
    passwordForm.value.code = code
}

// 关闭窗口
const TurnInversion = () => {
    toggleSwitch()
    initchangePassword()
}

const ruleFormRef = ref(null);
const passwdrules = reactive({
    oldpassword: [
        { required: true, message: '请输入旧密码', trigger: 'blur' },
        {
            pattern: /^[a-zA-Z0-9_]{6,12}$/,
            message: '6到12个数字、字母或下划线',
            trigger: 'blur'
        }
    ],
    newpassword: [
        { required: true, message: '请输入新密码', trigger: 'blur' },
        {
            pattern: /^[a-zA-Z0-9_]{6,12}$/,
            message: '6到12个数字、字母或下划线',
            trigger: 'blur'
        }
    ],
    newpassword2: [
        {
            required: true,
            message: '请再次输入新密码',
            trigger: 'blur'
        },
        {
            min: 6,
            max: 12,
            message: '6到12个字符',
            trigger: 'blur'
        },
        {
            validator: validateConfirmPassword,
            trigger: 'blur'
        }
    ],
})


// 修改密码提交
const changePassword = () => {
    ruleFormRef.value.validate((valid) => {
        if (valid) {
            passwordForm.value.code = props.code
            apiChangepassword(passwordForm.value).then(res => {
                if (res.code != 200) {
                    ElMessage.error(res.msg)
                } else {
                    ElMessage({
                        message: res.msg,
                        type: 'success',
                    })
                    emits('confirmGetData');
                    toggleSwitch()
                }
            });
        } else {
            console.log("表单验证未通过");
        }
    })
}
</script>
<style scoped lang='scss'></style>
