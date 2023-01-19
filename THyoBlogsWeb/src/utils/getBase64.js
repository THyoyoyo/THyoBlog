export const getBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = (e) => {
            resolve(reader.result);
        };
        reader.onerror = (error) => reject(error);
    });
};

export const imageToBase64 = (file, isCompression, callback) => {
    return new Promise((resolve, reject) => {
        // 判断图片类型
        if (file.type == 'image/jpeg' || file.type == 'image/png' || file.type == 'image/jpg') {
            var isJpg = true
        } else {
            jsJpg = false
        }

        // 判断图片大小
        const isLt2M = file.size / 1024 / 1024 < 2
        if (!isJpg) {
            this.$message.error('上传图片只能是jpg/png/jepg格式')
        }
        if (!isLt2M) {
            this.$message.error('上传图片大小不能超过2M')
        }

        // 创建一个HTML5的FileReader对象
        var reader = new FileReader()
        // 创建一个img对象
        var img = new Image()
        // let filename = file.filename
        if (file) {
            reader.readAsDataURL(file)
        }
        if (isJpg && isLt2M) {
            reader.onload = (e) => {
                // let base64Str = reader.result.split[','][1]
                img.src = e.target.result
                // base64地址图片加载完毕后执行
                img.onload = function () {
                    // 缩放图片需要canvas（也可以在DOM中直接定义canvas标签，这样就能把压缩完的图片不转base64也能直接显示出来）
                    var canvas = document.createElement('canvas')
                    var context = canvas.getContext('2d')
                    // 图片原始尺寸
                    var originWidth = this.width
                    var originHeight = this.height
                    // 最大尺寸限制，可通过设置宽高来实现图片压缩程度
                    var maxWidth = 1000,
                        maxHeight = 1000
                    // 目标尺寸
                    var targetWidth = originWidth
                    var targetHeight = originHeight
                    // 图片尺寸超过最大尺寸限制
                    if (originWidth > maxWidth || originHeight > maxHeight) {
                        if (originWidth / originHeight > maxWidth / maxHeight) {
                            // 更改宽度，按照宽度限定尺寸
                            targetWidth = maxWidth
                            targetHeight = Math.round(maxWidth * (originHeight / originWidth))
                        } else {
                            targetHeight = maxHeight
                            targetWidth = Math.round(maxHeight * (originWidth / originHeight))
                        }
                    }
                    // 对图片进行缩放
                    canvas.width = targetWidth
                    canvas.height = targetHeight
                    // 清除画布
                    context.clearRect(0, 0, targetWidth, targetHeight)
                    /** 图片压缩
                     * 第一个参数是创建的img对象
                     * 第二三个参数是左上角坐标
                     * 后两个参数是画布区域宽高
                     */
                    context.drawImage(img, 0, 0, targetWidth, targetHeight)
                    /** 压缩后的base64文件
                     * 第一个参数可以为image/jpeg或image/webp类型的图片
                     * 第二个参数设置图片质量取值0-1，超出则以默认值0.92替代
                     */
                    var newUrl = canvas.toDataURL('image/webp', 0.7)
                    if (isCompression) { // 返回压缩后的base64
                        resolve(newUrl)
                    } else { // 返回不压缩的base64
                        resolve(e.target.result)
                    }
                }
            }
            reader.onerror = (error) => reject(error);
        }
    });






}