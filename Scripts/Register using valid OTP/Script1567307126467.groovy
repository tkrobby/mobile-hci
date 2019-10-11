import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

Mobile.startApplication('/Users/macbookair/git/qa-mobil-hci/APK/base.apk', false)
Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

//getInfoDevice
device_Height = Mobile.getDeviceHeight()
device_Width = Mobile.getDeviceWidth()
int startY = device_Height / 2
int endY = startY
int startX = device_Width * 0.30
int endX = device_Width * 0.70
for (i = 0; i < 3; i++) {
    Mobile.swipe(endX, startY, startX, endY)
    Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)
}

Mobile.tap(findTestObject('Register/android.widget.TextView2 - DAFTAR'), 10)
Mobile.tap(findTestObject('Register/android.widget.TextView1 - Tanggal Lahir'), 10)
Mobile.tap(findTestObject('Register/android.widget.Button7 - OK Date'), 10)
Mobile.setText(findTestObject('Register/android.widget.EditText0 - Masukkan No. Handphone Anda'), '81394130621', 10)
Mobile.setText(findTestObject('Register/android.widget.EditText1 - Tentukan Kode PIN (4 Digit)'), '1212', 10)
Mobile.setText(findTestObject('Register/android.widget.EditText2 - Konfirmasi Kode PIN'), '1212', 10)
Mobile.tap(findTestObject('Register/android.widget.Button0 - LANJUTKAN'), 10)
Mobile.tap(findTestObject('Register/android.widget.TextView1 - Saya Setuju'), 10)
Mobile.delay(20, FailureHandling.STOP_ON_FAILURE)

Mobile.openNotifications()
//verify sms from HOME CREDIT
def smsOTP = Mobile.getText(findTestObject('Register/android.widget.TextView4 - HOME CREDIT'), 0)
Mobile.verifyEqual(smsOTP, 'HOME CREDIT')
//Get Content SMS
def sms = Mobile.getText(findTestObject('Object Repository/Register/android.view.ViewGroup8SMS'), 10)
println(sms)
//Read OTP
String otp = sms.substring(38, 42)
println(otp)
Mobile.closeNotifications()

Mobile.setText(findTestObject('Object Repository/Register/CreateAccount/android.widget.EditText0'), otp , 10)
Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('Object Repository/Register/CreateAccount/android.widget.Button0 - DAFTAR'), 10)
Mobile.closeApplication()
