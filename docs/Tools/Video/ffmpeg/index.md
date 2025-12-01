## Installation

### Macos

#### Download *ffmpeg* and *ffprobe*

Go to [https://ffmpeg.org/download.html](https://ffmpeg.org/download.html) and click the Apple logo in the "Get packages & executable files" section.

Click "Static builds for macOS 64-bit".

You'll see two options for downloading *ffmpeg*. Choose the one with the shorter filename; this will look like  `ffmpeg-<versionNumber>.7z` , where  `<versionNumber>`  is something like  `4.3.1` .

Underneath this heading, click "Download as ZIP".

Scroll down the page until you see ffprobe. Choosing the shorter filename, under  `ffprobe-<versionNumber>.7z` , click "Download the file as ZIP".

If a popup appears after clicking the download link, press "allow" or "save".

Open your *Downloads* folder, and double-click  `ffmpeg-<versionNumber>.zip` . This will extract it using the *Archive Utility* and create an executable  `ffmpeg`  file in *Downloads*.

Repeat this step for  `ffprobe` .

You should now have two executables, called  `ffmpeg`  and  `ffprobe` .

#### Move the downloaded files to the right location

Open your *home folder*.

Your *home folder* has the same name as your user account. The easiest way to find it is to open *Finder*, and use the keyboard shortcut  `command + shift + H`  or in the menu bar select *Go > Home*.

You should see folders such as *Desktop*, *Applications*, and *Downloads* in this folder.

Create a new folder called  `audio-orchestrator-ffmpeg`  in your home folder.

Go to *File > New folder* or use the shortcut  `command + shift + N` , type or enter the folder name, and press  `return`  to confirm.

Open your new  `audio-orchestrator-ffmpeg`  folder by double-clicking it.

Create a new folder called  `bin`  in  `audio-orchestrator-ffmpeg` .

Move the  `ffmpeg`  and  `ffprobe`  files from  `Downloads`  into this  `bin`  folder.

You should now have two files,  `ffmpeg`  and  `ffprobe` , in your  `~/audio-orchestrator-ffmpeg/bin/`  folder 
    ![Screenshot of a Finder window showing the ffmpeg and ffprobe executable files](https://bbc.github.io/bbcat-orchestration-docs/images/installation/ffmpeg.png){ loading=lazy }**ffmpeg and ffprobe executables with the required folder structure**  
#### Authorise *ffmpeg* and *ffprobe*

Double-click the file called  `ffmpeg` .

You should see an error message *"ffmpeg can’t be opened because it is from an unidentified developer"*. Click "OK".

Go to *System Preferences > Security and Privacy* and click on the *General* tab.

At the bottom of the window you will see a message saying that *ffmpeg* was blocked. Click "Open Anyway".

If you do not see this message in the General tab, double-click  `ffmpeg`  again.

You may have to click the "unlock" button and enter your password to be able to click "Open Anyway".

If you see another popup that says *“ffmpeg is from an unidentified developer. Are you sure you want to open it?”*, click "Open". If you don’t get this popup, just go to the same file and double-click it again.

When you double-click the file, a *Terminal* window may open. Keep the terminal open until you see a message confirming you can close it.

Repeat authorisation steps (a) to (f) for the file called  `ffprobe` .

## Arguments

-ss seek

-t duration

-to end time point

-i video.mp4 (must come after the above)

-c codec

-o output

## List of encoders

```bash
ffmpeg -encoders
```

## Get video FPS

```bash
ffmpeg -i filename 2>&1 | sed -n "s/.*, \(.*\) fp.*/\1/p"
```

## Collect all keyframes

```bash
ffmpeg -skip_frame nokey -i 2.flv -vsync 0 -r 30 -f
```

## Preview

```bash
ffplay -i video.mp4 -ss 00:01:02 -t 00:30:00
```

Start at 1 minute 2 seconds with a duration of 30 seconds

## Lossless

## Losslessly Trim

Leave a bit of extra padding in cut points to prevent overcut

```bash
  ffmpeg -ss 00:01:02 -t 00:30:00 -i video.mp4 -c copy video_clip.mp4
```

If you have trouble using the video in Final Cut Pro, try the .mov extension like this:

```bash
    ffmpeg -i video.mp4 -ss 00:01:02 -t 00:30:00 -codec copy video_clip.mov
```

## Lossless Concat/Merge

```python
  ffmpeg -f concat -safe 0 -i file_list.txt -c copy output.mp4
```

`file_list.txt`

```txt
    file 'clip1.mp4'
    file 'clip2.mp4'
    file 'clip3.mp4'
```

With chapters

```python
    import subprocess
    import os
    import re

    def make_chapters_metadata(list_mp4: list):
        print(f"Making metadata source file")

        chapters = {}
        for single_mp4 in list_mp4:
            number = single_mp4.removesuffix(".m4a")
            cmd = f"ffprobe -v quiet -of csv=p=0 -show_entries format=duration '{folder}/{single_mp4}'"
            print(f"{cmd=}")
            duration_in_microseconds_ = subprocess.run(cmd, shell=True, capture_output=True)
            duration_in_microseconds__ = duration_in_microseconds_.stdout.decode().strip().replace(".", "")
            print(f"{duration_in_microseconds_=}")
            duration_in_microseconds = int(duration_in_microseconds__)
            chapters[number] = {"duration": duration_in_microseconds}

        print(f"{chapters=}")
        chapters[list_mp4[0].removesuffix(".m4a")]["start"] = 0
        for n in range(1, len(chapters)):
            chapter = list_mp4[n-1].removesuffix(".m4a")
            next_chapter = list_mp4[n].removesuffix(".m4a")
            chapters[chapter]["end"] = chapters[chapter]["start"] + chapters[chapter]["duration"]
            chapters[next_chapter]["start"] = chapters[chapter]["end"] + 1
        last_chapter = list_mp4[len(chapters)-1].removesuffix(".m4a")
        chapters[last_chapter]["end"] = chapters[last_chapter]["start"] + chapters[last_chapter]["duration"]

        metadatafile = f"{folder}/combined.metadata.txt"
        with open(metadatafile, "w+") as m:
            m.writelines(";FFMETADATA1\n")
            for chapter in chapters:
                ch_meta = """
    [CHAPTER]
    TIMEBASE=1/1000000
    START={}
    END={}
    title={}
    """.format(chapters[chapter]["start"], chapters[chapter]["end"], chapter)
                m.writelines(ch_meta)


    def concatenate_all_to_one_with_chapters():
        print(f"Concatenating list of mp4 to combined.mp4")
        metadatafile = f"{folder}/combined.metadata.txt"
        subprocess.run(["ffmpeg", "-hide_banner", "-y", "-safe", "0", "-f", "concat", "-i", "list_mp4.txt", "-c", "copy", "-i", f"{metadatafile}", "-map_metadata", "1", "combined.m4a"])

    if __name__ == '__main__':

        folder = "."  ## Specify folder where the files 0001.mp4... are
        ## concatenate_all_to_one_with_chapters()
        ## exit(0)

        list_mp4 = [f for f in os.listdir(folder) if f.endswith('.m4a')]
        list_mp4.sort()
        print(f"{list_mp4=}")

        ## Make the list of mp4 in ffmpeg format
        if os.path.isfile("list_mp4.txt"):
            os.remove("list_mp4.txt")
        for filename_mp4 in list_mp4:
            with open("list_mp4.txt", "a") as f:
                line = f"file '{filename_mp4}'\n"
                f.write(line)

        make_chapters_metadata(list_mp4)
        concatenate_all_to_one_with_chapters()
```

## Lossless Speed Change

```bash
  ffmpeg -itsscale 1/{new_speed} -i input.mp4 -c copy output.mp4
```

## Lossless Compression/Timelapse (extract only i-frames)

```bash
  ## drop non-keyframes
  ffmpeg -itsscale 1/{new_speed} -i input.mov -c:v copy -an -bsf:v "noise=drop=not(key)" output.mp4

  ## select every k frames
  ## won't work all some frames won't be keyframes (use method 3 instead)
  ffmpeg -itsscale 1/{new_speed} -i input.mov -c:v copy -an -bsf:v "noise=drop=mod(n\,{select_frame_frequency})" output.mp4

  ## both
  ffmpeg -itsscale 1/{new_speed} -i input.mov -c:v copy -an -bsf:v "noise=drop=not(key),noise=drop=mod(n\,select_frame_frequency)" output.mp4
```

## ffprobe

## Limit frames

```bash
  ffprobe -read_intervals {intervals} -i {input_file}
```

```
  INTERVAL  ::= [START|+START_OFFSET][%[END|+END_OFFSET]]
  INTERVALS ::= INTERVAL[,INTERVALS]
```

## Get keyframe timestamps

```bash
  ffprobe -select_streams v -show_entries frame=pict_type,pts_time -of csv=p=0 -skip_frame nokey -v 0 -hide_banner -i input.mp4
```

## Get number of keyframes

```bash
  ffprobe -hide_banner -of compact=p=0:nk=1 -select_streams v:0 -count_frames -show_entries stream=nb_read_frames -skip_frame nokey -v 0 -i input.mp4
```

## Get video duration

```bash
  ## only time points
  ffprobe -select_streams v -show_entries frame=pts_time -of csv=p=0 -skip_frame nokey -v 0 -hide_banner -i INPUT.mov

  ffprobe -v error -show_entries format=duration -of default=noprint_wrappers=1:nokey=1 -i input.mp4
```

Get keyframe interval

```python
  ffprobe -read_intervals {start_time_to_read}%+{max_seconds_to_read} -select_streams v -show_entries frame=pts_time -of csv=p=0 -skip_frame nokey -v 0 -hide_banner -i {input_file}

  ## another approach
  ffprobe -loglevel error -skip_frame nokey -select_streams v:0 -show_entries frame=pkt_pts_time -of csv=print_section=0 input.mp4
```

## ffplay

```bash
ffplay -fs -noborder -nostats input.mp4
```

## Video Filters

## Split Screen

```bash
  ffmpeg -i input_1.mp4 -i input_2.mp4 -filter_complex "[0:v:0]pad=iw*2:ih[bg]; [bg][1:v:0]overlay=w" output.mp4
```

## Lens Correction

```bash
  ffmpeg -i in.mp4 -vf "lenscorrection=cx=0.5:cy=0.5:k1=-0.227:k2=-0.022" out.mp4
```

## Stabilization

```bash
  ffmpeg -i input.mp4 -vf vidstabdetect=shakiness=10 -f null -
  ffmpeg -i input.mp4 -vf vidstabtransform=smoothing=50,unsharp=5:5:0.8:3:3:0.4 output.mp4
```

## Color Correction

```bash
  ffmpeg -i input.mp4 -vf eq=brightness=1.2:contrast=1.2:saturation=2 -c:a copy output.mp4
```

## Remove Duplicate Frames

Mpdecimate

```bash
    ffmpeg -i input.mp4 -vf mpdecimate,setpts=N/FRAME_RATE/TB -vsync vfr -an -c:a copy output.mp4
```

Lossless

```bash
    #!/bin/bash

    ## lld -- lossless decimator/de-duplicator

    ## This script takes a compatible all-I-frame video file and losslessly deduplicates to a new video file.
    ## The new file will have the same fps as the old file, though. Changing fps is left to the user --
    ##   it's not simple for e.g. ProRes to do this and remain "legal".
    #
    ## You may want to tweak the configuration of mpdecimate below. For me, "hi" is disabled, because even between my
    ## duplicate frames there typically existed one 8x8 block that would exceed a high value, for some reason.
    ## lo and frac may need tweaking for your source material: https://ffmpeg.org/ffmpeg-filters.html#mpdecimate
    ## After running, you can investigate the output of mpdecimate in the temp directory file mpdecimate.txt.
    #
    ## This script assumes no audio associated with the footage -- it would likely need to be modified to handle
    ## footage with audio
    #
    ## max frame number in file is 99,999,999 frames (~38 days at 29.97fps)
    #
    ## Development/discussion thread here: https://forum.videohelp.com/threads/383274-de-duplicating-decimating-ProRes-losslessly#post2483571

    echo -e "\nlld version 1.0\n"

    if [ -z $1 ]; then echo -e "Usage: ddpr file [expected_fps [debug]]\n  where expected_fps is the actual frame rate (used for info purposes only -- will not affect processing)\n  note: lld will create file.tempdir in current directory\n  If you specify \"debug\", lld will leave the temporary directory behind for evaluating logs, etc.\n"; exit; fi

    fname=$1
    newfps=$2
    debug=$3

    if [ ! -f "$fname" ]; then
      echo "$fname does not exist. Doing nothing."
      exit 1
    fi

    if [ -e "$fname".tempdir ]; then
      echo "$fname".tempdir already exists. Doing nothing.
      exit 1
    fi

    if [ -e "$fname.dedup.mov" ]; then
      echo "$fname.dedup.mov already exists. Doing nothing."
      exit 1
    fi

    fext=`echo $fname | sed -r 's/.*(\.[^.]*)$/\1/'`
    fextcnt=`echo -n $fext | wc -c`

    if [ $fextcnt -le 1 ]; then
      echo "Couldn't detect filename extension. Doing nothing."
      exit 1
    fi

    mkdir "$fname".tempdir
    mkdir "$fname".tempdir/frames

    cd "$fname".tempdir

    fps=`ffprobe -v 0 -of compact=p=0 -select_streams 0 -show_entries stream=r_frame_rate ../$fname | sed 's/^r_frame_rate=/scale=15;/g' | bc` 

    echo "$fps fps detected. Detecting duplicates..."

    flen=`echo "scale=15;1/$fps" | bc`
    flenoffset=`echo "scale=15;.1*$flen" | bc`

    ffmpeg -i ../$fname -vf mpdecimate=max=1:hi=999999999:lo=64*3:frac=0.4 -loglevel debug -f null - > mpdecimate.txt 2>&1

    fcnt=`cat mpdecimate.txt | grep "frames successfully decoded" | sed -r 's/^(.*) frames .*$/\1/'`

    cat mpdecimate.txt | grep Parsed | grep keep | sed -r 's/^.*pts_time:(.*) drop.*/\1/' > ts.txt

    if [ ! -f ts.txt ]; then
      echo "ts.txt not created. Exiting."
      exit 1
    fi

    res=`cat ts.txt | wc -l`

    if [ $res -le "0" ]; then
     echo "ts.txt didn't generate correctly. Exiting."
     exit 1
    fi

    ## The offset version is necessary because apparently ffmpeg doesn't find the nearest frame to the timestamp, but the next frame.
    ## As a result, occasional rounding errors mean that the wrong frame would be targeted when extracting them from the original file.
    ## The offset is a 10% backwards shift in the timestamp to guarantee that the next frame found will be the correct frame.
    cat ts.txt | awk "{print \$1-$flenoffset}" | bc > ts2.txt

    if [ ! -f ts2.txt ]; then
      echo "ts2.txt not created. Exiting."
      exit 1
    fi

    res=`cat ts2.txt | wc -l`

    if [ $res -le "0" ]; then
     echo "ts2.txt didn't generate correctly. Exiting."
     exit 1
    fi

    newfr=`echo "scale=3;$fps*($res/$fcnt)" | bc`

    echo "Detected $res good frames out of $fcnt total frames = detected actual frame rate of $newfr fps"

    if [ ! -z $newfps ]; then
      fpserr=`echo "scale=3;100*($newfr/$newfps - 1)" | bc`

      fpserrfirstchar=`echo $fpserr | sed -r 's/^(.).*$/\1/g'`
      if [ $fpserrfirstchar == "." ]; then
        fpserr=`echo 0$fpserr`
      fi
      fpserrfirstchars=`echo $fpserr | sed -r 's/^(..).*$/\1/g'`
      if [ $fpserrfirstchars == "-." ]; then
        fpserr=`echo $fpserr | sed -r 's/^.(.*)$/\1/g'`
        fpserr=`echo "-0"$fpserr`
      fi

      echo "$fpserr% error from expected $newfps fps."
    fi

    echo "Generating segment video files..."

    ## doing -ss after -i because before is not accurate in this case for some reason (first few seconds work fine, then starts going off the rails)
    cat ts2.txt | awk "{printf \"ffmpeg -i ../$1 -ss %s -t 0$flen -vcodec copy -acodec copy frames/%08d$fext\n\",\$1,f;f++}" > ffmpeg_frame_extraction_commands.txt

    if [ ! -f ffmpeg_frame_extraction_commands.txt ]; then
      echo "ffmpeg_frame_extraction_commands.txt not created. Exiting."
      exit 1
    fi

    res=`cat ffmpeg_frame_extraction_commands.txt | wc -l`

    if [ $res -le "0" ]; then
     echo "ffmpeg_frame_extraction_commands.txt didn't generate correctly. Exiting."
     exit 1
    fi

    source ffmpeg_frame_extraction_commands.txt > ffmpeg_frame_extraction_results.log 2>&1

    res=`/bin/ls -1 frames/*$fext | wc -l`

    if [ $res -le "0" ]; then
      echo "No segment files generated. Exiting."
      exit 1
    fi

    echo "Generating segment logfile for concatenation and concatenating..."

    /bin/ls -1 frames/*$fext | sed -r "s/(.*)/file '\1'/" > segment_files_list.txt

    if [ ! -f segment_files_list.txt ]; then
      echo "segment_files_list.txt not created. Exiting."
      exit 1
    fi

    res=`cat segment_files_list.txt | wc -l`

    if [ $res -le "0" ]; then
     echo "segment_files_list.txt didn't generate correctly. Exiting."
     exit 1
    fi

    ffmpeg -f concat -i segment_files_list.txt -c copy ../$fname.dedup$fext > ffmpeg_concatenation_results.log 2>&1

    if [ -z $debug ]; then
     echo "Deleting temporary directory. (Use e.g. \"lld file.mov 18 debug\" to prevent this.)"
     echo "Removing temporary directory: $fname.tempdir"
     cd ..
     rm -rf "$fname".tempdir
    else
     echo "Temporary directory $fname.tempdir left behind."
    fi

    echo -e "Done. Resulting filename: $fname.dedup$fext\n"
```

## Motion Blur

```bash
  ffmpeg -i input.mp4 -vf tmix=frames={no_of_frames_to_blend} output.mp4
```

## Upscaling

```bash
  ffmpeg -i input.mp4 -vf "scale={scale}:flags=neighbor" -c:v nvenc_hevc -crf 30 -preset ultrafast output.mp4
```

Scale

```
    3840:-1
    -1:2160
    iw*2:ih*2
```

## Web Optimization

```bash
ffmpeg -i input.mp4 -c copy -movflags faststart output.mp4
```

Fast start is for internet streaming as it puts header at the begining of the file. When you play file from HDD it doesn't matter

Only for MP4, M4A, M4V, MOV
