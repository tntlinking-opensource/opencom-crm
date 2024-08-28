<style lang="css">
  .pull-left {
    float: left;
  }

  .pull-right {
    float: right;
  }

  .padding {
    padding: 10px;
  }

  .print {
    font-size: 14px;
    color: #333;
    font-family: -apple-system,system-ui,BlinkMacSystemFont,Roboto,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB","Microsoft YaHei",sans-serif;
  }

  .print .title {
    font-weight: bold;
    font-size: 18px;
    text-align: center;
    margin-bottom: 10px;
  }

  .print .table-print {
    border-spacing: 0;
    border-collapse: collapse;
    border: 1px solid #333;
    width: 100%;
    table-layout: fixed;
  }

  .print .table-print th {
    border: 1px solid #333;
    text-align: center;
    height: 30px;
  }

  .print .table-print td {
    border: 1px solid #333;
    height: 30px;
    padding-left: 5px;
  }
</style>
<!-- 提供打印盒子：独立样式控制 -->
<template>
  <section :style="{display: preview ? 'block' : 'none'}">
    <div id="help_print" class="print" style="width: 21cm;">
      <slot></slot>
    </div>
  </section>
</template>

<script>
  import 'print-js';

  export default {
    name: 'print',
    props: {
      preview: {
        default: false,
        type: Boolean
      }
    },
    data () {
      return {
        loading: true
      };
    },
    methods: {
      print () {
        printJS({
          printable: 'help_print',
          type: 'html',
          scanStyles: false,
          style: `
                    .pull-left {
                        float: left;
                    }

                    .pull-right {
                        float: right;
                    }

                    .padding {
                        padding: 10px;
                    }

                    .print {
                        font-size: 14px;
                        color: #333;
                        font-family: -apple-system,system-ui,BlinkMacSystemFont,Roboto,"Helvetica Neue",Arial,"PingFang SC","Hiragino Sans GB","Microsoft YaHei",sans-serif;
                    }

                    .print .title {
                        font-weight: bold;
                        font-size: 18px;
                        text-align: center;
                        margin-bottom: 10px;
                    }

                    .print .table-print {
                        border-spacing: 0;
                        border-collapse: collapse;
                        border: 1px solid #333;
                        width: 100%;
                        table-layout: fixed;
                    }

                    .print .table-print th {
                        border: 1px solid #333;
                        text-align: center;
                        height: 30px;
                    }

                    .print .table-print td {
                        border: 1px solid #333;
                        height: 30px;
                        padding-left: 5px;
                    }
                `
        });
      }
    }
  };
</script>
