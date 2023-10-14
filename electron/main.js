// Modules to control application life and create native browser window
const { app, BrowserWindow } = require('electron')
const path = require('node:path')
const treeKill = require("tree-kill");
const {spawn} = require("child_process");
const kill = require("tree-kill");

function createWindow () {
  // Create the browser window.
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js')
    }
  })

  // and load the index.html of the app.
  // mainWindow.loadFile('index.html')
  mainWindow.loadURL('http://localhost:8080')

  // Open the DevTools.
  // mainWindow.webContents.openDevTools()
}

let child = null;
let possibleMatches = [];
let childPID = null;

async function spawnBackend() {
  let jarPath = app.getAppPath() + '\\jar\\Stirling-PDF-0.14.5.jar';
  console.log('jarPath ' + jarPath);
  child = require('child_process').spawn( 'java', ['-jar', jarPath, ''] );
  console.log(child);

  let scriptOutput = "";

  child.stdout.setEncoding('utf8');
  child.stdout.on('data', function(data) {
    //Here is where the output goes

    const regex = "INFO [0-9]+"

    if (childPID === null) {
      possibleMatches = data.match(regex) !== null ? data.match(regex) : [];
      if (possibleMatches.length > 0 ) {
        if (possibleMatches[0].includes('INFO')) childPID = possibleMatches[0].replace('INFO ', '');
      }
      console.log(childPID)
    }

    console.log('stdout: ' + data);

    data=data.toString();
    scriptOutput+=data;
  });

  child.stderr.setEncoding('utf8');
  child.stderr.on('data', function(data) {
    //Here is where the error output goes

    console.log('stderr: ' + data);

    data=data.toString();
    scriptOutput+=data;
  });

  child.on('close', function(code) {
    //Here you can get the exit code of the script

    console.log('closing code: ' + code);

    console.log('Full output of script: ',scriptOutput);
  })
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.whenReady().then(async () => {
  await spawnBackend();
  createWindow()

  app.on('activate', async function () {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (BrowserWindow.getAllWindows().length === 0) {

      createWindow();
    }
  })
})

// Quit when all windows are closed, except on macOS. There, it's common
// for applications and their menu bar to stay active until the user quits
// explicitly with Cmd + Q.
app.on('window-all-closed', async () => {
  if (process.platform !== 'darwin') app.quit()
  let kill = require('tree-kill');
  console.log('child pid ' + child.pid);
  console.log('child pid of child ' + childPID);

  await kill(+childPID);
  await kill(child.pid);
})

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
