const Generator = require('yeoman-generator');
const {once} = require('events');
const nfs = require('fs');
const glob = require('glob');

module.exports = class extends Generator {
  /**
   * Provides prompts for scaffolding
   * @return {Promise}
   */
  prompting() {
    const prompts = [
      {
        type: 'input',
        name: 'folders',
        message: '(1/1) Where are the folders to grab deployment scripts from?' +
         '(comma delimited, Glob patterns accepted)',
        default: '**/kubernetes.yaml',
      },
    ];

    return this.prompt(prompts).then((answers) => {
      this.answers = answers;
    });
  }

  /**
   * Write out files for scaffolding
   */
  writing() {
    this._generateDeployment();
  }

  /**
   * Generates kubernetes deployment file
   */
  _generateDeployment() {
    const patterns = this.answers['folders'].split(',');
    let files = [];
    for (const pattern of patterns) {
      files = files.concat(glob.sync(pattern));
    }
    console.log('Loading files: ', files);
    this._loadFiles(files);
  }

  /**
   * Returns contents from each yml file
   * @param {Array} files List of files to loop through
   */
  * _generate(files) {
    if (!files || files.length === 0) return;
    let index = 0;
    for (const file of files) {
      if (index > 0) yield '\n---\n';
      yield this.fs.read(file);
      index++;
    }
  }

  /**
   * Reads from kubernetes deployement files and generates one file
   * @param {Array} files List of files to prepare kubernetes deployment scripts from
   */
  async _loadFiles(files) {
    const writeable = nfs.createWriteStream('./kubernetes.yml');

    for await (const chunk of this._generate(files)) {
      // Handle backpressure on write
      if (!writeable.write(chunk)) {
        await once(writeable, 'drain');
      }
    }

    writeable.end();
    // Ensure completion without errors
    await once(writeable, 'finish');
  }
};
