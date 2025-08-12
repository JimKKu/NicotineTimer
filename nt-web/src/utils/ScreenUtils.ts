import { ref, onMounted, onBeforeUnmount } from 'vue'

export function useFullscreen() {
    const isFullscreen = ref(false)

    function enterFullscreen(element: HTMLElement = document.documentElement) {
        const requestMethod =
            element.requestFullscreen ||
            (element as any).webkitRequestFullscreen ||
            (element as any).mozRequestFullScreen ||
            (element as any).msRequestFullscreen

        if (requestMethod) {
            requestMethod.call(element)
        } else {
            console.warn('当前浏览器不支持全屏 API')
        }
    }

    function exitFullscreen() {
        const exitMethod =
            document.exitFullscreen ||
            (document as any).webkitExitFullscreen ||
            (document as any).mozCancelFullScreen ||
            (document as any).msExitFullscreen

        if (exitMethod) {
            exitMethod.call(document)
        }
    }

    function toggleFullscreen(element: HTMLElement = document.documentElement) {
        if (!isFullscreen.value) {
            enterFullscreen(element)
        } else {
            exitFullscreen()
        }
    }

    function handleFullscreenChange() {
        isFullscreen.value = !!(
            document.fullscreenElement ||
            (document as any).webkitFullscreenElement ||
            (document as any).mozFullScreenElement ||
            (document as any).msFullscreenElement
        )
    }

    onMounted(() => {
        document.addEventListener('fullscreenchange', handleFullscreenChange)
        document.addEventListener('webkitfullscreenchange', handleFullscreenChange)
        document.addEventListener('mozfullscreenchange', handleFullscreenChange)
        document.addEventListener('MSFullscreenChange', handleFullscreenChange)
    })

    onBeforeUnmount(() => {
        document.removeEventListener('fullscreenchange', handleFullscreenChange)
        document.removeEventListener('webkitfullscreenchange', handleFullscreenChange)
        document.removeEventListener('mozfullscreenchange', handleFullscreenChange)
        document.removeEventListener('MSFullscreenChange', handleFullscreenChange)
    })

    return {
        isFullscreen,
        enterFullscreen,
        exitFullscreen,
        toggleFullscreen,
    }
}