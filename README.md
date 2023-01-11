# Flip-Display

A flip display simulator for Android.

You can easily apply to your Android project and use it.

The idea comes from a video on YouTube:
[![Split Flap Display by Oat Foundry | Old School Departures Boards](https://res.cloudinary.com/marcomontalbano/image/upload/v1673423472/video_to_markdown/images/youtube--F8wx-h_sfR0-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://youtu.be/F8wx-h_sfR0 "Split Flap Display by Oat Foundry | Old School Departures Boards")


I thought it is very cool. Then I implemented a simulator on Android. 

## How to Add to your Project:
[![](https://jitpack.io/v/UnicornJin/Flip-Display.svg)](https://jitpack.io/#UnicornJin/Flip-Display)

Step 1. 

Add the JitPack repository to your build file

Add it in your root settings.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. 

In your app level build.gradle, add the dependency:

	dependencies {
	        implementation 'com.github.UnicornJin:Flip-Display:0.0.2'
	}

## How does it look like:

The widget simulates the old-school flip board. 

[![pSnGseg.gif](https://s1.ax1x.com/2023/01/11/pSnGseg.gif)]


## How to use
You can check out the "flipdisplayreferenceapp" for the example of using this widget.

There are a lot of comments in the code, you can refer to them.

I suggest using the views as a matrix. Put them all into a list for easy management.

The module provides a container to hold the messages:
        
    DisplayContent: is a container to hold messages
        DisplayContent(width: int, height: int):
            construct with given width and height
        setRow(rowNumber: int, message: string): 
            It provides methods to set message on one row
        getCharAt(row: int, column: int):
            Get the char at one position

You can define several messages you want to display on the matrix with the DisplayContent, and update your FlipCharViews. Go to reference app folder's MainActivity for details.
(Remember to consider concurrent programming)

You can customized these things:
    
    // default char list
    // if current displaying is 'Z', and you update the content to 'A'
    // the board will flip all "a-z", "0-9", and the special chars 
    // and back to beginning, until it shows 'A'
    // 
    // when you initialize a FlipCharView, the default showing
    // char is blank spack ' '
    private var charList = mutableListOf<Char>(
        ' ',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        ':', ',', '/'
    )

    // You can update this char list:
    // Add new char to it
    fun addToCharList(newChar: Char) {
        charList.add(newChar)
    }
    // Just use a new charList
    fun replaceCharList(newCharList: MutableList<Char>) {
        charList = newCharList
    }

    // These attributes are updated in View declaration

    // View background color, default darkgray
        <attr name="mViewBackgroundColor" format="color" />

    // Font color, default white
        <attr name="mViewFontColor" format="color" />
    
    // The gap width between the two boards, default 3f
        <attr name="mGap" format="dimension" />
    
    // The size of round corner, default 20f
        <attr name="mCornerRadius" format="dimension" />
    
    // The time needed for each flip animation, default 100 (means 0.1s per flip)
        <attr name="mAnimationLength" format="integer" />


## Development Plan:
    - ViewGroup: For ease of management, just declare a view matrix and put in messages, no need to update view by view by yourself.
    - more customizable settings
    - more animation based on user feedback (Put your suggestions in Issues)

## Author's Personal Blog:

blog.unicornjin.com 

Welcome to check it out!
