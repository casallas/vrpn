//
//
// Please edit package-info.html, then paste here
//

/**
 * 


	This package include a set of widgets that are bound to a Vrpn server
	buttons, analogs and trackers.


	<h3>Create a simple application that uses the widgets</h3>
	<p>
		The following chapters describe how to create a simple application
		with a {@link eu.ensam.ii.vrpn.VrpnToggleButton VrpnToggleButton} and
		a {@link eu.ensam.ii.vrpn.VrpnSeekBar VrpnSeekBar}. An example
		application project that contains these widgets and more can be found
		in the <em>example_app</em> directory.
	</p>
	<h4>Create and configure the project</h4>
	<p>
		Refer to any Android documentation for instructions about how to
		set-up the Android SDK and the Eclipse plugin. Do not forget to setup
		the Android SDK in any new workspace (<em>Window</em> &gt; <em>Preferences</em>,
		then go to the <em>Android</em> tab and configure the SDK location).
	</p>
	<p>
		Import the <em>Vrpn_library</em> project into your Eclipse workspace.
		Open the Android section of the project property page and verify that
		<em>Is library</em> checked. If the build complains about "Android
		requires .class compatibility set to 5.0", you need first to select
		the project, right-click > <em>Properties</em> &gt; <em>Android
			tools</em> &gt; <em>Fix project properties</em>, then open the project
		properties, go to the <em>Java compiler</em> section, then deselect
		<em>enable project specific settings</em>. You may need to clean and
		rebuild (<em>Project</em> &gt; <em>Clean</em>) several times before
		the project shows no errors.
	</p>

	<p>
		Create a new Android project : <em>File</em> > <em>New</em> > <em>Other
			...</em> , <em>Android</em> &gt; <em>Android Project</em>. On the first
		page of the wizard, check <em>Create activity</em> and enter any name
		you like for the main activity (for instance <em>MainActivity</em>).
	</p>

	<p>
		Add the library to your project : open the properties of the project,
		go to the <em>Android</em> page. In the <em>Library</em> pane, click <em>Add</em>
		then select <em>vrpn_library</em>, apply the changes and close the
		window. At this point, the Eclipse <em>Package Explorer</em> view of
		your project should contain a <em>vrpn_library_src</em> folder and the
		<em>gen</em> folder should contain a package entry named <em>eu.ensam.ii.vrpn</em>.
	</p>
	<p>
		Open the
		<code>AndroidManifest.xml</code>
		file. On the <em>Application</em> tab, check <em>Define an
			&lt;Application&gt; tag in the AndroidManifest.xml</em>. Then click on the
		<em>Browse</em> button that is on the right of <em>Name</em> and
		select <em>VrpnApplication</em>.
	</p>

	<h4>Modify the main activity</h4>


	<p>Edit the main activity to define the binding to the Vrpn server
		when the activity is created. The method {@link
		android.app.Activity#onCreate onCreate} is called when the Activity is
		created. The default version of this method contain only the first and
		last lines :</p>
	<pre>
  super.onCreate(savedInstanceState);
  ....    	
  setContentView(R.layout.main);
</pre>
	<p>We need to define the binding with the Vrpn server. The method
		becomes :</p>
	<pre>
&#64;Override
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);  
  InetAddress vrpnServerAddress = null;
  try {
    // Replace the IP address
    vrpnServerAddress = InetAddress.getByName("192.168.173.1");
  } catch (UnknownHostException e) {
  }
  final int vrpnServerPort = 7777;
  VrpnClient.getInstance().setupVrpnServer(vrpnServerAddress, vrpnServerPort);
  
  setContentView(R.layout.main);
}
</pre>
	<a name="vrpnids"></a>
	<h4>Create a Vrpn Ids list</h4>
	<p>
		Before laying out your Vrpn widgets in your application, you should
		create an XML configuration file that contains the IDs of the Vrpn
		buttons and analog that you will be using. This allows you to keep a
		unique list of Vrpn IDs, making it easier to avoid duplicate values
		and reference this list from your application. Create this file with <em>File</em>
		> <em>New</em> > <em>Other</em>, then <em>Android </em> > <em>Android
			XML File</em>. In XML file dialog, type any file name you like, for
		instance <em>vrpn.xml</em>, then select <em>Values</em> as the type of
		resource to create.
	</p>
	<p>
		Edit the resulting file, found at <em>res/values/vrpn.xml</em> to add
		the numbers of the Vrpn Buttons and Analogs that you will be using.
	</p>
	<pre>
&lt;resources&gt;
  &lt;item type="id" format="integer" name="VrpnAnalogLightIntensity"&gt;0&lt;/item&gt;
  &lt;item type="id" format="integer" name="VrpnButtonLightsOn"&gt;0&lt;/item&gt;
&lt;/resources&gt;
</pre>

	<p>
		Note that <em>type</em> must be <em>id</em> and <em>format</em> must
		be <em>integer</em>, but <em>name</em> can be anything you like.
	</p>

	<h4>Add your widgets to the main layout</h4>
	<p>Say we want an Android application to control the lighting in
		our VR application : turn the light on or off and also control the
		light intensity. We will create an application with a {@link
		eu.ensam.ii.vrpn.VrpnToggleButton VrpnToggleButton} as the light
		switch and a {@link eu.ensam.ii.vrpn.VrpnSeekBar VrpnSeekBar} as a
		light intensity controller.</p>
	<p>
		By default, the layout of the main Activity is described in <em>res/layout/main.xml</em>
		and just displays <em>Hello</em>. Edit this file. The XML code looks
		is something like :
	</p>
	<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;LinearLayout 
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:orientation="vertical"
  android:layout_width="fill_parent"
  android:layout_height="fill_parent"
  &gt;
  &lt;TextView  
    android:layout_width="fill_parent" 
    android:layout_height="wrap_content" 
    android:text="@string/hello"
  /&gt;
&lt;/LinearLayout&gt;
</pre>

	<p>
		The root element,
		<code>LinearLayout</code>
		, is a container for the other widgets, currently contains a
		<code>TextView</code>
		.
	<p>
		The basics about XML layouts are presented in <a
			href="http://developer.android.com/guide/topics/ui/declaring-layout.html">Declaring
			layout</a> SDK page.
	</p>
	<p>
		Before the end tag
		<code>&lt;/LinearLayout&gt;</code>
		, include a {@link eu.ensam.ii.vrpn.VrpnToggleButton VrpnToggleButton}
		to turn the lights on or off :
	<pre>
&lt;eu.ensam.ii.vrpn.VrpnToggleButton
  android:layout_width="fill_parent" 
  android:layout_height="wrap_content" 
  app:vrpnButton="@id/VrpnButtonLightsOn"
/&gt;
</pre>

	The Vrpn widgets use so-called
	<em>custom XML properties</em> , such as
	<em>app:vrpnButton</em> . This custom property allows you to specify
	the id of the target Vrpn button in the XML layout file rather than in
	Java code. However, the use of this custom property requires the
	addition of an XML namespace tag so that the build system can find
	then. After the
	<em>xmlns:android</em> line, add the following line :
	<pre>
  xmlns:app="http://schemas.android.com/apk/res/your.application.package.name.here"
</pre>
	Different attributes exist for each widgets and the whole list is shown
	when you unfold
	<em>gen/eu/ensam.ii.vrpn/R.java/R/attr</em> in the Eclipse Package
	View. Your layout is now ready for your to include the Vrpn widgets.
	Now save the file to build your project. If there are build errors,
	check the following :
	<ul>
		<li>you have added the <code>xmlns::app</code> line in the root
			layout</li>
		<li>the spelling of the <code>app:</code> attribute</li>
		<li>the value of the Vrpn attributes</li>
		<li>the spelling of the widget name</li>
	</ul>

	<p>
		In most cases, the <em>Preview</em> tab of the XML layout is not
		displayed properly as soon as you include one of the Vrpn widgets.
		This is a known problem for which not solution has currently been
		found. It prevents you from previewing the layout it wil be displayed
		correctly in the application, either on a device or in the emulator.
	</p>

	<p>Now add a {@link eu.ensam.ii.vrpn.VrpnSeekBar VrpnSeekBar} to
		control the light intensity :</p>
	<pre>
&lt;eu.ensam.ii.vrpn.VrpnSeekBar
  android:layout_width="fill_parent" 
  android:layout_height="wrap_content"
  app:minValue="0"
  app:maxValue="255"
  app:defaultValue="128"
  app:vrpnAnalog="@id/VrpnAnalogLightIntensity"
/&gt;
</pre>
	Your Android application is now ready to run. Upload on a device and
	run. There is not much to seen for the time being. However, if you run
	a network sniffer on a machine connected to the same network as the
	Android device, you should see UDP packets flowing out of the device.
	If you are using the emulator instead of an actual device, see the
	<a
		href="
http://developer.android.com/guide/developing/devices/emulator.html#emulatornetworking">Emulator
		networking</a> SDK page for the correct network setup.
	<h4>Test your application</h4>
	In order to test your application, you need a VRPN server built with
	VRPN_USE_JSONNET. See the
	<code>README.jsoncpp</code>
	file for instructions to build this server. When you get this server
	running, the easiest way to test the Android application is to use the
	<code>vrpn_print_devices</code>
	application provided with Vrpn. Type the command :
	<pre>
vrpn_print_devices Jsonnet@localhost
</pre>
	The command should then report the values of the Button and SeekBar as
	they are changed.

	<h3>Beyond the basics</h3>
	<h4>Application with multiple tabs</h4>
	If you want to create an application with more widgets that can be
	placed on a single screen, using a {@link android.app.TabActivity
	TabActivity} instead of a regular Activity may be the way to go. A
	{@link android.app.TabActivity TabActivity} can display several
	activities of view, each inside a tab. The Android SDK documentation
	explains how to create a tabbed application with activities. However,
	using activities require careful state management since the activities
	may be terminated when the use switches from one tab to another. It is
	therefore easier to manage a {@link android.app.TabActivity
	TabActivity} that display
	<em>views</em> intead of activities.
	<p></p>
	<p>First of all, your main activity must extend {@link
		android.app.TabActivity TabActivity} intead of {@link
		android.app.Activity Activity}.</p>
	<p>
		Then, create a layout for each tab in <em>res/layout</em> with <em>File</em>
		> <em>New</em> > <em>Other...</em> , then <em>Android</em> > <em>New
			XML file</em>. In the <em>New Android XML file</em> dialog, name the file
		<em>tab_one.xml</em>, select <em>Layout</em> as the type of resource,
		then select <em>LinearLayout</em> as the root element. Repeat the
		process with a second tab layout named <em>tab_two.xml</em>. Update
		each tab layout as described in <em>Add your widgets to the main
			layout</em> above, paying attention not to forget the <em>xmlns:app</em>
		line.
	</p>
	<p>
		The next step is to create a new layout names <em>res/layout/tab_group.xml</em>for
		the whole activity. This layout looks like :
	</p>
	<pre>
&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;TabHost
  xmlns:android="http://schemas.android.com/apk/res/android"
  android:id="@android:id/tabhost"
  android:layout_width="fill_parent"
  android:layout_height="fill_parent"&gt;
  &lt;TabWidget
    android:id="@android:id/tabs"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
  /&gt;
  &lt;FrameLayout
    android:id="@android:id/tabcontent"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:padding="5dp"&gt;
  &lt;/FrameLayout&gt;
  &lt;include layout="@layout/tab_one"&gt;&lt;/include&gt;
  &lt;include layout="@layout/tab_two"&gt;&lt;/include&gt;
&lt;/TabHost&gt;
</pre>

	<p>
		Then edit the <em>onCreate</em> method of your activity. After the
		line <em>super.onCreate()</em> add the <em>VrpnClient.getInstance().setUri(....)</em>
		line, then add :
	<pre>
  LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  inflater.inflate(R.layout.tab_group, getTabHost().getTabContentView(), true);
  TabHost.TabSpec spec;
  spec = getTabHost().newTabSpec("1").setContent(R.id.tab_one);
  getTabHost().addTab(spec)
  spec = getTabHost().newTabSpec("2").setContent(R.id.tab_two);
  getTabHost().addTab(spec);        
  getTabHost().setCurrentTab(0);
</pre>
	<p>See in the SDK documentation how to use the {@link
		android.widget.TabHost.TabSpec#setIndicator setIndicator} method in
		order to display an icon in the tab headers.</p>
	<h3>Updating the widget library</h3>
	<h4>Create a new Widget</h4>
	<p>To create a new Widget type you should look at the source code
		of VrpnSeekBar, which is the more elaborate. The main steps are as
		follows :</p>
	<ul>
		<li>Decide which Android widget class you want to extend and
			which events will trigger Vrpn updates.</li>
		<li>Decide whether your widget will send Vrpn button,analog or
			tracker updates.</li>
		<li>Decide whether your widget needs new custom XML attributes.
			If this is the case, look at <code>res/values/attrs.xml</code> for
			examples.</li>
		<li>Implement a private <em>init</em> method that each
			constructor will call. In this method, you must obtain the XML custom
			attributes of the widget. You should also send a Vrpn update with the
			initial value of the widget</li>
		<li>Implement the appropriate change listener, according to which
			event you want your widget to respond to. In the change listener,
			call the appropriate method : {@link
			eu.ensam.ii.vrpn.VrpnClient#sendAnalog(int,double) sendAnalog} or
			{@link eu.ensam.ii.vrpn.VrpnClient#sendButton(int,boolean)
			sendButton}. Note that a single widget can s end several updates,
			like the {@link eu.ensam.ii.vrpn.VrpnSurface VrpnSurface} does or
			like a <a
			href="http://developer.android.com/resources/samples/ApiDemos/src/com/example/android/apis/graphics/ColorPickerDialog.html">color
				picker</a>would do. There is currently no <em>sendTracker</em> method
			although this may be useful to implement an Arcball for instance.</li>
	</ul>
	<p>Note that using the full path of the parent class such as in :
	<pre>public class VrpnRadioButton extends android.widget.RadioButton</pre>
	allows Javadoc to generate a link to the parent class.
	</p>

	<h4>Generate the documentation</h4>
	<p>
		Select the <em>vrpn_library/src</em> folder, then select the menu item
		<em>Project</em> &gt; <em>Generate Javadoc</em>.
	<p>
		On the first page of the wizard, select the path of the <em>javadoc</em>
		tool. Check that <em>vrpn_library/src</em> is selected, but not <em>vrpn_library/gen</em>,
		then select an appropriate destination for the documentation.
	</p>
	<p>
		On the second page of the wizard select <em>android.jar</em> in the <em>Referenced
			archives</em> list and take note of the <em>android.jar</em> doc path
		(like
		<code>file:/E:/android/docs/reference</code>
		).
	</p>
	<p>
		On the third page of the wizard add the following line to the <em>Extra
			javadoc options</em> text box :
	<pre>
-linkoffline http://d.android.com/reference file:/E:/android/docs/reference
</pre>
	but replace the
	<code>file:/</code>
	URL by your local Android SDK URL noted from the page two of the
	wizard. This allows Javadoc to include full references to the on-line
	Android documentation.
	
/*
 ********************************* End of paste Here
 */

package eu.ensam.ii.vrpn;