module Jekyll
  class DirectoryIndexGenerator < Generator
    safe true
    
    def generate(site)
      # Get all markdown files
      markdown_files = site.pages.select { |page| page.path.end_with?('.md') && !page.path.end_with?('index.md') && !page.path.end_with?('README.md') }
      
      # Group files by directory
      directories = {}
      markdown_files.each do |page|
        dir = File.dirname(page.path)
        directories[dir] ||= []
        directories[dir] << page
      end
      
      # Generate index files for each directory
      directories.each do |dir, pages|
        next if dir == '.' || site.pages.any? { |p| p.path == "#{dir}/index.md" }
        
        # Create a new page for this directory
        index = PageWithoutAFile.new(site, site.source, dir, "index.md")
        title = File.basename(dir).split('-').map(&:capitalize).join(' ')
        index.content = "---\nlayout: directory_index\ntitle: #{title}\n---\n\n# #{title}\n"
        index.data['directory_files'] = pages
        site.pages << index
      end
    end
  end
  
  # A Page-derived class that doesn't require the file to exist on disk
  class PageWithoutAFile < Page
    def initialize(site, base, dir, name)
      @site = site
      @base = base
      @dir  = dir
      @name = name
      
      self.process(name)
      self.data ||= {}
    end
    
    def read_yaml(*)
      # No file to read from
    end
  end
end
