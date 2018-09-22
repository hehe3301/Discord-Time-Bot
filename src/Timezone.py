import time
__author__="Alex Habermann"
__date__ ="$Sep 18, 2018 5:55:26 PM$"

glob_debug=2;
glob_tz_file='short.csv'
#glob_tz_file='timezone.csv'
glob_al_file='aliases.csv'
glob_timezones = {}
glob_aliases = {}
glob_dst = {}

def isDst():
    print bool(time.localtime().tm_isdst)
    return bool(time.localtime().tm_isdst)

def loadTz(p_filename):
    if(glob_debug>0): print "Loading: "+p_filename
    with open(p_filename, 'r') as file:
        line = file.readline().strip()
        line_arr=line.split(',')
        count = 0
        while line:
            line_arr=line.split(',')
            abrev =str(line_arr[1].strip('"')).lower()
            offset=int(line_arr[3].strip('"'))/(60*60)
            if(glob_debug>1): print "Adding: "+str(abrev)+" at GMT"+str(offset)
            glob_timezones[abrev]=offset
            count+=1
            line = file.readline().strip()
        #end while
        if(glob_debug>0): print"Total lines: " +str(count)+", Total TZ: "+str(len(glob_timezones))
    #end with
    if(glob_debug>0): print "File Closed!"
#end loadTz

def addAlias(p_alias, p_standard, b_saving=""):
    saving=b_saving.lower()
    if saving=="":
        saving=p_standard
    glob_aliases[p_alias.lower()]=(p_standard.lower(),saving)

def loadAliases(p_filename):
    if(glob_debug>0): print "Loading: "+p_filename
    with open(p_filename, 'r') as file:
        line = file.readline().strip()
        line_arr=line.split(',')
        count = 0
        while line:
            line_arr=line.split(',')
            alias =str(line_arr[0].strip('"')).lower()
            if len(line_arr)>2:
                standard=str(line_arr[1].strip('"')).lower()
                savings=str(line_arr[2].strip('"')).lower()
            else:
                standard=str(line_arr[1].strip('"')).lower()
                savings=str(line_arr[1].strip('"')).lower()
                
            if(glob_debug>1): print "Adding alias: "+str(alias)+" to ("+str(standard)+", "+str(savings)+")"
            addAlias(alias, standard, savings)
            count+=1
            line = file.readline().strip()
        #end while
        if(glob_debug>0): print"Total lines: " +str(count)+", Total aliases: "+str(len(glob_timezones))
    #end with
    if(glob_debug>0): print "File Closed!"
#end loadAliases

def getAlias(p_some_alias):
    if p_some_alias.lower() in glob_aliases:
        if(glob_debug>1): print "Found Alias: "+p_some_alias+" to: "+str(glob_aliases[p_some_alias.lower()])
        return glob_aliases[p_some_alias.lower()][time.localtime().tm_isdst]
    return p_some_alias


def cleanTime(p_some_time):
    if ":" in p_some_time:
        if(glob_debug>0):print "HH:MM time: "+str(p_some_time)
        return cleanTime(str(p_some_time).replace(":",""))
    elif "am"in p_some_time:
        if(glob_debug>0):print "AM time: "+str(p_some_time)
        return cleanTime(str(p_some_time).replace("am",""))
    elif "pm"in p_some_time:
        if(glob_debug>0):print "PM time: "+str(p_some_time)
        tmp = p_some_time.replace("pm","") #0913 9 09 945
        if len(tmp)<3: #if we are one or two digit string
            tmp = str(int(tmp)+12) + "00" #0913 945 #add 12 and add 0's
        else: #we are 3 or four
            tmp = str((int(tmp[:-2])%12)+12) + tmp[-2:] #add 12 to the hour
        return cleanTime(tmp)
    elif len(p_some_time)<3: #9 09 12
        if(glob_debug>0): print "Shortest time: "+str(p_some_time)
        return cleanTime(p_some_time+"00")
    elif len(p_some_time)<4: #900
        if(glob_debug>0): print "Short time: "+str(p_some_time)
        return cleanTime("0"+p_some_time)
    else:
        if(glob_debug>0): print "Military time: "+str(p_some_time)
        return p_some_time
#end cleanTime

def changeTime( p_time_string="now",p_src_tz="GMT", p_dest_tz="GMT"):
    """
    @p_time_string: String abrevation for the source timezone
    @p_src_tz: String abrevation for the source timezone
    @p_dest_tz: String abrevation for the destination timezone
    """
    now_gmt=time.gmtime()
    time_string=p_time_string.lower()
    src_tz=p_src_tz.lower()
    dest_tz=p_dest_tz.lower()

    if src_tz in glob_aliases:
        src_tz=getAlias(src_tz)
    if dest_tz in glob_aliases:
        dest_tz=getAlias(dest_tz)
        
    if(glob_debug>0): print "Converting: "+p_time_string+" from: '"+p_src_tz+"' to: '"+p_dest_tz+"'"
    if src_tz not in glob_timezones:
        if(glob_debug>0): print "Unkown Source Timezone: " + p_src_tz
        return "Unkown Source Timezone: " + p_src_tz
    if dest_tz not in glob_timezones:
        if(glob_debug>0): print "Unkown Destination Timezone: " + p_src_tz
        return "Unkown Destination Timezone: " + p_src_tz
    
    if time_string=="now":
        time_string=cleanTime(str(now_gmt.tm_hour) + str(now_gmt.tm_min))
        dest_tz=src_tz
        src_tz="gmt"
    else:
        time_string=cleanTime(time_string)
    time_string=str(int(time_string[0:2])-glob_timezones[src_tz]+glob_timezones[dest_tz])+time_string[-2:]
    if int(time_string[0:2])>=24 :
        time_string= str(int(time_string[0:2])%24)+time_string[-2:]
    time_string=time_string
    time_string="'"+p_time_string+"-"+p_src_tz.upper()+"' is: "+cleanTime(time_string)+"-"+p_dest_tz.upper()
    return time_string

#end changeTime

def init():
    loadTz(glob_tz_file)
    loadAliases(glob_al_file)
#end changeTime

def main():
    init()
    print glob_aliases
    print changeTime( "1800","EST", "GMT" )
    print changeTime( "12PM","EST" )
    print changeTime( "13" )
    print changeTime("7pm", "Eastern", "Zulu")
    print "Eastern".lower() in glob_aliases


    
if __name__ == "__main__":
    main()