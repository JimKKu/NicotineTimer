<template>

</template>


<script setup lang="ts">
const {chart_flag,pic_flag,mode} = defineProps<{
  chart_flag: boolean,
  pic_flag: boolean,
  mode: string
}>()


</script>


<style scoped>
.word-small {
  font-size: 24px;
  color: #00bd7e;
  font-weight: bold;
  padding: 8px;
}
.days {
  position: relative;
  font-size: 24px;
  padding: 5px;
  color: #00bd7e;
}
.units {
  font-size: 12px;
  font-weight: lighter;
}
.container-small {
  position: absolute;
  transform:  translate(-50%, -50%);
  transition: all 0.2s;
}

.container-small-top {
  top: 36%;
}
.container-small-bottom {
  top: 50%
}
.container-small-right {
  left: 30%;
}
.container-small-left {
  left: 28%;
}
.container-big {
  padding: 18px;
  border-radius: 16px;
  width: 900px;
  height: 180px;
  position: absolute;
  transform:  translate(-50%, -50%);
  transition: all 0.2s;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;

  display: flex;
  flex-direction: raw;

  text-align: center;
  color: white;
  .word-big {
    position: absolute;
    left: 50%;
    top: 110%;
    transform: translateX(-50%);
    color: gray;
    border-bottom: 1px solid #cdcdcd;
    span {
      color: #00bd7e;
      font-size: 24px;
      font-weight: bold;
      padding: 8px;
    }
  }

  .settings {
    width: 400px;
    height: 600px;
    position: absolute;
    left: -50%;
    top: 50%;
    transform: translate(-50%,-50%);
    box-shadow: 0px 0px 0px transparent,0px 0px 0px transparent,2px 2px 6px #8e8e8e inset,-2px -2px 6px #ffffff inset;
    border-radius: 26px;
  }
  .big-timer {
    margin: auto;
    width: 144px;
    height: 144px;
    box-sizing: border-box;
    box-shadow: 0px 0px 0px transparent,0px 0px 0px transparent,2px 2px 6px #8e8e8e inset,-2px -2px 6px #ffffff inset;
    border-radius: 20px;
    line-height: 144px;
    color: gray;
    font-size: 100px;
    font-family:
        "Segoe UI",         /* Windows */
        "Helvetica Neue",   /* macOS */
        "Noto Sans",        /* Linux 通用 */
        "Liberation Sans",  /* Linux fallback */
        sans-serif;
    font-weight: bold;
    letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
    background: #e1e1e1;
    transition:  all .2s;

    text-shadow:
        1px 1px 2px #fff,
        -1px -1px 2px #aaa;
  }

  .big-day,
  .big-timer:hover {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff,0px 0px 0px transparent inset,0px 0px 0px transparent inset;
    border: 0 solid #fff;
    font-size: 106px;
  }
  .big-unit {
    position: relative;
    top: 116px;
    right: -94px;
    color: rgba(128, 128, 128, 0.489);
  }
  .big-mill{
    font-size: 58px;
    line-height: 66px;
    padding: 6px;
    color: #00bd7e;
    box-shadow: none;
    border: 1px solid;
    box-sizing: border-box;
  }

  .big-mill:hover {
    font-size: 62px;
  }

  /* 时钟跳动动画效果 */
  .beating {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff,0px 0px 0px transparent inset,0px 0px 0px transparent inset;
    border: 0 solid #fff;
    font-size: 106px;
  }
  .big-second {
    overflow: hidden;

    div {
      font-weight: bold;
      letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
      transition:  all .2s;

      text-shadow:
          1px 1px 2px #fff,
          -1px -1px 2px #aaa;

      span{
        display: inline-block;
        font-weight: bold;
        letter-spacing: 0.5px; /* 稍微拉开一点字间距 */
        transition:  all .2s;

        text-shadow:
            1px 1px 2px #fff,
            -1px -1px 2px #aaa;
      }
    }
  }

}

.container-big-left {
  left: 40%;
}
.container-big-right {
  left: 50%;
}
.container-big-top {
  top: 40%;
}
.container-big-bottom {
  top: 50%;
}

.container-set {
  width: 620px;
  height: 130px;
  position: absolute;
  left: 50%;
  transform: translate(-50%,-50%);
  transition: all 0.2s;
  border-radius: 6px;
  padding: 26px;
  box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;

  button {
    width: 80px;
    height: 28px;
    position: relative;
    text-align: right;
    border-radius: 4px;
    color: gray;
    transition:  all .2s;
    border: 1px solid gray;

    svg {
      width: 20px;
      height: 20px;
      position: absolute;
      left: 8px;
    }

  }

  button:hover {
    box-shadow: 2px 2px 6px #8e8e8e,-2px -2px 6px #fff;
    border: 1px solid transparent;
  }
}

.container-set-top {
  top: 40%;
}
.container-set-bottom {
  top: 50%;
}

</style>
