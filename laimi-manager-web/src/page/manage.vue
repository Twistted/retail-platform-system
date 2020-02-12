<style lang="less" scoped>
		@import '../style/mixin';
		.header{
			padding:10px 15px;
			background-color: rgba(14, 110, 165,1);
		}
		.logo{display: inline-block;}
		.logo img{
			width: 200px;
			height: 60px;
		}
		.right{float:right;}
		.right-header{
			width:50px;
			height: 50px;
			display: inline-block;
			border-radius: 100%;
			background-color: #ddd;
		}
		.right .vm{vertical-align: middle}
</style>
<template>
	<div class="manage_page fillcontain">
		<div class="header">
			<div class="logo"><img src="http://img.tuku.cn/file_thumb/201709/m2017092708563243.jpg" alt=""></div>
			<div class="right">
				<i class="el-icon-location"></i><span class="vm">番禺广场店</span>&nbsp;&nbsp;&nbsp;
				<span class="vm">超级管理员</span>
				<el-dropdown trigger="click" class="vm">
					<div class="right-header">
						<!-- <img src="../assets/img/user.png" alt=""> -->
					</div>
					<el-dropdown-menu slot="dropdown">
						<el-dropdown-item>设置</el-dropdown-item>
						<el-dropdown-item>退出</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>
		</div>
		<el-row style="height:calc(100% - 84px);overflow:hidden;">
	  		<el-col :span="4"  style="height: 100%; background-color: #324057;overflow-y:auto;overflow-x:hidden">
				<el-menu :default-active="defaultActive" style="min-height: 100%;" theme="dark" router>
					<el-menu-item index="manage"><i class="el-icon-menu"></i>首页</el-menu-item>
					<el-submenu index="2">
						<template slot="title"><i class="el-icon-document"></i>数据管理</template>
						<el-menu-item index="userList">用户列表</el-menu-item>
						<el-menu-item index="shopList">商家列表</el-menu-item>
						<el-menu-item index="foodList">食品列表</el-menu-item>
						<el-menu-item index="orderList">订单列表</el-menu-item>
						<el-menu-item index="adminList">管理员列表</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<template slot="title"><i class="el-icon-plus"></i>添加数据</template>
						<el-menu-item index="addShop">添加商铺</el-menu-item>
						<el-menu-item index="addGoods">添加商品</el-menu-item>
					</el-submenu>
					<el-submenu index="4">
						<template slot="title"><i class="el-icon-star-on"></i>图表</template>
						<el-menu-item index="visitor">用户分布</el-menu-item>
						<!-- <el-menu-item index="newMember">用户数据</el-menu-item> -->
					</el-submenu>
					<el-submenu index="5">
						<template slot="title"><i class="el-icon-edit"></i>编辑</template>
						<!-- <el-menu-item index="uploadImg">上传图片</el-menu-item> -->
						<el-menu-item index="vueEdit">文本编辑</el-menu-item>
					</el-submenu>
					<el-submenu index="6">
						<template slot="title"><i class="el-icon-setting"></i>设置</template>
						<el-menu-item index="adminSet">管理员设置</el-menu-item>
						<!-- <el-menu-item index="sendMessage">发送通知</el-menu-item> -->
					</el-submenu>
					<el-submenu index="7">
						<template slot="title"><i class="el-icon-warning"></i>说明</template>
						<el-menu-item index="explain">说明</el-menu-item>
					</el-submenu>
				</el-menu>
			</el-col>
			<el-col :span="20" style="height: 100%;overflow: auto;">
				<el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
					<el-tab-pane
						:key="item.name"
						v-for="(item, index) in editableTabs"
						:label="item.title"
						:name="item.name"
					>
						{{item.content}}
					</el-tab-pane>
				</el-tabs>
				<keep-alive>
				    <router-view></router-view>
				</keep-alive>

			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		computed: {
			defaultActive: function(){
				return this.$route.path.replace('/', '');
			}
		},
    }
</script>



