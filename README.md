ohos-segmented-control
=========================
ohos-Segmented is a custom component for ohos which is based on RadioGroup and RadioButton widget.

![Sample Image](screenshoot/shoot1.png)

## Including in your project

##### Add segmented_control to your project

    1.Copy the Ohos_segmented_control/library folder to the project directory

    2.Modify settings.gradle under the project and add the dependency on this module as follows:

    include ':demo', ':library'

    3. Introduce the dependency of the module under the project. Taking the entry module as an example, you need to modify the build.gradle file under the entry module to add the dependency:

    compile project(path: ':library') or implementation project(':library')

    Solution 2: local use of har package integration
    1. Compile this project, copy the har package generated in the build directory of the FlexLayout/library folder to the project lib folder:
    directory：\Ohos_segmented_control\library\build\outputs\har\debug\library-debug.har

    2. Add the following code in the entry's gradle
    implementation fileTree(dir:'libs', include: ['*.jar','*.har'])


More on the  configuration can be found in the Project.

Usage
-----
Define in xml like as follow
Sample code:
```xml
        <info.hoang8f.ohos.library.SegmentedGroup
            ohos:id="$+id:segmented4"
            ohos:width="match_content"
            ohos:height="80vp"
            ohos:margin="10vp"
            ohos:orientation="horizontal"
            app:sc_border_width="1vp"
            app:sc_corner_radius="5vp"
            app:sc_tint_color="#F44336">

            <info.hoang8f.ohos.library.AwesomeRadioButton
                ohos:id="$+id:button41"
                ohos:width="match_content"
                ohos:height="match_content"
                app:awesome_text="apple"
                ohos:min_width="100vp"
                ohos:text_size="13fp"
                ohos:min_height="26vp"
                ohos:text_alignment="center"
                />

            <info.hoang8f.ohos.library.AwesomeRadioButton
                ohos:id="$+id:button42"
                ohos:width="match_content"
                ohos:height="match_content"
                app:awesome_text="VS"
                ohos:min_width="100vp"
                ohos:text_size="13fp"
                ohos:min_height="26vp"
                ohos:text_alignment="center"
                />

            <info.hoang8f.ohos.library.AwesomeRadioButton
                ohos:id="$+id:button43"
                ohos:width="match_content"
                ohos:text_alignment="center"
                ohos:height="match_content"
                app:awesome_text="google"
                ohos:min_width="100vp"
                ohos:text_size="13fp"
                ohos:min_height="26vp"

                />
        </info.hoang8f.ohos.library.SegmentedGroup>
```

You also can be change the tint color and title color when button is checked by `setTintColor` method.
Here is sample code:

```java
SegmentedGroup segmented5 = (SegmentedGroup) findComponentById(ResourceTable.Id_segmented5);
segmented5.setTintColor(Color.DKGRAY);
```
If you dont specify border_width and/or corner_radius the default values will be used (1vp for border_width and 5 vp for corner_radius)

Credits
-------
Author:
* Le Van Hoang (@hoang8f)
* Added support for vertical RadioGroup by [tchar](https://github.com/tchar).
* Translate for openharmonyos by ..

License
-------
    The MIT License (MIT)

    Copyright (c) 2014 Le Van Hoang

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

