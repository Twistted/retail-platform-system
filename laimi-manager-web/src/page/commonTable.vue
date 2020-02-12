
<!-- table 列表 -->
<template>
	<div class="table">
        <div class="table-head" id="table-head" v-if="options.search && options.search.length > 0" style="overflow:hidden">
            <div v-for="(item, index) in searchValue" :key="index">
                <el-input v-model="item.value" :placeholder="item.placeholder" size="small" :style="{width: item.width}" v-if="item.type == 'input'" @keyup.enter.native="ajaxRequest(1)"></el-input>
                <el-date-picker v-model="item.value" :placeholder="item.placeholder" :type="item.dateType||'date'" size="small" :style="{width: item.width}" v-if="item.type == 'date'"></el-date-picker>
                <el-select v-model="item.value" :placeholder="item.placeholder" :clearable="item.clearable" :multiple="item.multiple" v-if="item.type == 'select'" size="small" :style="{width: item.width, marginRight: '4px'}" @change="selectChange(item.function, item.value)">
                    <el-option v-for="item in item.option" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
                <el-select v-model="item.value" :placeholder="item.placeholder" :clearable="item.clearable" v-if="item.type == 'select-staff'" size="small" :style="{width: item.width, marginRight: '4px'}">
                    <el-option v-for="item in option.shopStaff" :key="item.value" :label="item.label" :value="item.value"></el-option>
                </el-select>
            </div>
            <el-button type="primary" size="small" @click="ajaxRequest(1)">查询</el-button>
            <!-- <el-button type="primary" size="small" @click="exportTable(1)" :loading="download.loading" style="float:right">导出文件</el-button> -->
        </div>
        <div class="table-main">
            <el-table class="jui-table" :data="table.data" :height="table.height" border stripe v-loading="table.loading" element-loading-text="正在请求数据 ···">
                <div v-for="(item, index) in options.head" :key="index">
                    <el-table-column v-if="!item.format"
                        :prop="item.prop"
                        :label="item.label"
                        :width="item.width"
                        :align="item.align"
                        :min-width="item.minWidth || ''">
                    </el-table-column>
                    <el-table-column v-else
                        :prop="item.prop"
                        :label="item.label"
                        :width="item.width"
                        :align="item.align"
                        :min-width="item.minWidth || ''">
                        <div scope="scope">
                            {{ item.format(scope.row[item.prop]) }}
                        </div>
                    </el-table-column>
                </div>
                <el-table-column label="操作" :width="btnOption.width || 120" align="center" v-if="btnOption && btnOption.btn.length > 0">
                    <div scope="scope">
                        <el-button class="fnc-btn" :type="item.type" size="small" v-for="(item, index) in btnOption.btn" :key="index" @click="clickTableBtn(index, item.text, scope)">{{ item.text }}</el-button>
                    </div>
                </el-table-column>
            </el-table>
        </div>
        <div class="table-foot" v-if="options.pager != 'none'">
            <el-select v-model="pager.pageSize" size="small" @change="ajaxRequest(1)">
                <el-option v-for="item in [10, 25, 50, 80]" :key="item" :label="item + ' 条/页'" :value="item"></el-option>
            </el-select>
            <el-pagination :current-page.sync="pager.currentPage" :page-size="pager.pageSize" layout="prev, pager, next, jumper" :total="total" @current-change="ajaxRequest"></el-pagination>
            <span class="total">共 {{ total }} 条数据</span>
        </div>
        <!-- <a id="downExel" :download="(options.fileName || '文件') + download.date + '.xlsx'"></a> -->
    </div>
</template>
<script>
    export default {
        created: function() {
            console.log(this.options);
            
            if (this.options.height) {
                this.table.height = this.options.height
            } else {
                var _this = this
                setTimeout(function () {
                    var headHeight = document.getElementById('table-head').clientHeight
                    if (headHeight > 45) {
                        _this.table.height = document.body.clientHeight - (_this.options.pager == 'none' ? 0 : 45) - (_this.options.search && _this.options.search.length != 0 ? headHeight : 0) - 60
                    }
                }, 0)
            }

            if (this.options.search) this.searchValue = this.options.search.slice()

            for (var i = 0, m = this.searchValue.length; i < m; i++) {
                if (this.searchValue[i].type == 'select-staff') {
                    this.searchStaff()
                    break
                }
            }

            // this.ajaxRequest()
        },
        data: function() {
            return {
                searchValue: [],
                pager: {
                    currentPage: 1,
                    pageSize: 25
                },
                total: 100,
                table: {
                    data: [],
                    //height: document.body.clientHeight - (this.options.pager == 'none' ? 0 : 45) - (this.options.search && this.options.search.length != 0 ? 45 : 0) - 60,
                    loading: false
                },
                option: {
                    shopStaff: []
                },
                download: {
                    loading: false,
                    //date: dateFormat(new Date(), '-yyyy年MM月dd日hh时mm分ss秒')
                }
                
            }
        },
        watch: {},
        props: {
            options: Object,
            btnOption: Object
        },
        methods: {
            clickTableBtn: function(index, label, data) {
                this.$emit('btn-callback', index, label, { index: data.$index, param: data.row })
            },
            /**
             * @description 方法继承？
             */
            selectChange: function (_function, value) {
                if (typeof _function == 'function') _function(value)
            },
            /**
             * @description 请求接口
             */
            ajaxRequest: function(currentPage) {
                var _this = this

                if (!isNaN(currentPage)) this.pager.currentPage = currentPage

                // 初始化查询条件
                var searchValue = {}
                for (var i = 0, m = this.searchValue.length; i < m; i++) {
                    // 如果此类型为时间，则格式化时间
                    if (this.searchValue[i].type == 'date') {
                        searchValue[this.searchValue[i].prop] = this.searchValue[i].value ? dateFormat(new Date(this.searchValue[i].value), this.searchValue.format || 'yyyy-MM-dd') : ''
                    } else {
                        if(this.searchValue[i].multiple){
                            searchValue[this.searchValue[i].prop] = this.searchValue[i].value.length!=0? this.searchValue[i].value.join(","):"";
                        }else{
                            searchValue[this.searchValue[i].prop] = this.searchValue[i].value

                        }
                    }
                }
                var strUrl = this.options.apiUrl
                console.log(strUrl);
                
                // if (strUrl.indexOf("?") != -1) {
                //     var i = strUrl.indexOf("?") + 1
                //     var str = strUrl.substring(i, strUrl.length);
                //     strs = str.split("&");
                //     for (var i = 0; i < strs.length; i++) {
                //         var sTemp = strs[i].split("=");
                //         searchValue[sTemp[0]] = (sTemp[1]);
                //     };

                // }

                // this.table.loading = true

                // $.ajax({
                //     type: 'post',
                //     url: this.options.apiUrl,
                //     dataType: 'json',
                //     data: JSON.stringify({
                //         searchValue: searchValue,
                //         pager: this.pager
                //     }),
                //     contentType: "application/json",
                //     success: function(response) {
                //         if (response.code !== '0') return _this.$message.error(response.desc)

                //         _this.table.data = response.data.pageData
                //         _this.total = response.data.totalRows
                //     },
                //     complete: function() {
                //         _this.table.loading = false
                //     }
                // })
            },
            /**
             * @description 导出报表
             */
            exportTable: function() {
                var _this = this

                // 初始化查询条件
                var searchValue = {}
                for (var i = 0, m = this.searchValue.length; i < m; i++) {
                    // 如果此类型为时间，则格式化时间
                    if (this.searchValue[i].type == 'date') {
                        searchValue[this.searchValue[i].prop] = this.searchValue[i].value ? dateFormat(new Date(this.searchValue[i].value), this.searchValue.format || 'yyyy-MM-dd') : ''
                    } else {
                        if(this.searchValue[i].multiple){
                            searchValue[this.searchValue[i].prop] = this.searchValue[i].value.length!=0? this.searchValue[i].value.join(","):"";
                        }else{
                            searchValue[this.searchValue[i].prop] = this.searchValue[i].value

                        }
                    }
                }

                this.download.loading = true

                // $.ajax({
                //     type: 'post',
                //     url: this.options.apiUrl,
                //     dataType: 'json',
                //     data: JSON.stringify({
                //         searchValue: searchValue,
                //         pager: {
                //             currentPage: 1,
                //             pageSize: 999999
                //         }
                //     }),
                //     contentType: "application/json",
                //     success: function(response) {
                //         if (response.code !== '0') return _this.$message.error(response.desc)

                //         var head = {}
                //         for (var i = 0, m = _this.options.head.length; i < m; i++) {
                //             head[_this.options.head[i].prop] = _this.options.head[i].label
                //         }

                //         _this.download.date = dateFormat(new Date(), '-yyyy年MM月dd日hh时mm分ss秒')
                //         createExcelData(head, response.data.pageData, 'downExel', _this)
                //     },
                //     complete: function() {
                //         _this.download.loading = false
                //     }
                // })
            },
            /**
             * @description 查询店铺员工
             */
            searchStaff: function() {
                var _this = this
                // $.ajax({
                //     url: baseURL + '/contactext/contactExtApiAction!ajaxSearchUsers.action?dqdp_csrf_token=' + dqdp_csrf_token,
                //     dataType: 'json',
                //     type: 'post',
                //     success: function(response) {
                //         if (response.code != '0') return _this.$message.error(response.desc)
                //         var arr = []
                //         for (var i in response.data.options) {
                //             arr.push({ value: response.data.options[i].id, label: response.data.options[i].name })
                //         }
                //         _this.option.shopStaff = arr
                //     }
                // })
            }
        }
    }
</script>